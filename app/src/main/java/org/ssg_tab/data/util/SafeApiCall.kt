package org.ssg_tab.data.util

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <T> safeApiCall(block: () -> T): Result<T> {
    return runCatching { block() }
        .fold(
            onSuccess = { Result.success(it) },
            onFailure = { throwable ->
                val processedException = when (throwable) {
                    is HttpException -> HttpResponseHandler.parseHttpException(throwable)
                    is SocketTimeoutException -> Exception("네트워크 연결 시간이 초과되었습니다.")
                    is UnknownHostException -> Exception("인터넷 연결을 확인해주세요.")
                    is IOException -> Exception("네트워크 오류가 발생했습니다.")
                    else -> Exception(throwable.message ?: "알 수 없는 오류가 발생했습니다.")
                }
                Result.failure(processedException)
            }
        )
}