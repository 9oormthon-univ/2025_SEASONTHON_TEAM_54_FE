package org.ssg_tab.data.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.ssg_tab.core.network.TokenManager
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            tokenManager.getAccessToken()
        }

        Timber.d("kakao AccessToken, AuthInterceptor: Bearer $token")

        val requestBuilder = chain.request().newBuilder()
        if (token.isNotBlank()) {
            requestBuilder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
