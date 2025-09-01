
package org.ssg_tab.data.util

/**
 * HTTP 응답 관련 커스텀 예외 클래스
 * @param code HTTP 상태 코드
 * @param message 오류 메시지
 */
class HttpResponseException(
    val code: Int,
    override val message: String
) : Exception(message) {

    override fun toString(): String {
        return "HttpResponseException(code=$code, message='$message')"
    }
}

/**
 * HTTP 상태 코드별 편의 팩토리 함수들
 */
object HttpExceptions {
    fun badRequest(message: String = "Bad Request") = HttpResponseException(400, message)
    fun unauthorized(message: String = "Unauthorized") = HttpResponseException(401, message)
    fun forbidden(message: String = "Forbidden") = HttpResponseException(403, message)
    fun notFound(message: String = "Not Found") = HttpResponseException(404, message)
    fun conflict(message: String = "Conflict") = HttpResponseException(409, message)
    fun internalServerError(message: String = "Internal Server Error") = HttpResponseException(500, message)
}