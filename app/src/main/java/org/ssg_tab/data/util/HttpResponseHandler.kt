package org.ssg_tab.data.util

import kotlinx.serialization.json.Json
import org.ssg_tab.data.dto.response.base.ApiResponse
import org.ssg_tab.data.dto.response.base.NullableApiResponse
import retrofit2.HttpException
import timber.log.Timber

object HttpResponseHandler {
    private val json = Json { ignoreUnknownKeys = true }

    fun <T> ApiResponse<T>.handleApiResponse(): Result<T> =
        if (this.isSuccess) {
            Result.success(this.result)
        } else {
            Result.failure(Exception("API Error: ${this.message}"))
        }

//    fun <T> NullableApiResponse<T>.handleNullableApiResponse(): Result<T?> =
//        if (this.status in 200..299) {
//            Result.success(this.data)
//        } else {
//            Result.failure(Exception("Unknown error : ${this.message}"))
//        }

    fun parseHttpException(e: Throwable): Exception {
        return when (e) {
            is HttpException -> {
                try {
                    val errorBody = e.response()?.errorBody()?.string()
                    if (errorBody.isNullOrBlank()) {
                        return HttpResponseException(e.code(), "Empty error response")
                    }

                    val errorResponse = try {
                        json.decodeFromString<NullableApiResponse<Unit>>(errorBody)
                    } catch (jsonException: Exception) {
                        Timber.e(jsonException, "JSON 파싱 실패")
                        NullableApiResponse(
                            status = e.code(),
                            code = e.message(),
                            message = "Failed to parse error response",
                            data = null
                        )
                    }
                    when (errorResponse.status) {
                        400 -> HttpResponseException(400, "Bad Request : ${errorResponse.message}")
                        401 -> HttpResponseException(401, "Unauthorized : ${errorResponse.message}")
                        403 -> HttpResponseException(403, "Forbidden : ${errorResponse.message}")
                        404 -> HttpResponseException(404, "Not Found : ${errorResponse.message}")
                        409 -> HttpResponseException(409, "Conflict : ${errorResponse.message}")
                        500 -> HttpResponseException(500, "Internal Server Error : ${errorResponse.message}")
                        else -> HttpResponseException(errorResponse.status, "Unknown error : ${errorResponse.message}")
                    }
                } catch (ex: Exception) {
                    Exception("Failed to parse error response.")
                }
            }
            is java.net.SocketTimeoutException -> Exception("네트워크 연결 시간이 초과되었습니다.")
            is java.net.UnknownHostException -> Exception("인터넷 연결을 확인해주세요.")
            is java.io.IOException -> Exception("네트워크 오류가 발생했습니다.")
            else -> Exception(e.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}