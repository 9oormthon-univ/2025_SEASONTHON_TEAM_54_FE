package org.ssg_tab.presentation.ui.login.model

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.request.SignInRequestDto
import org.ssg_tab.domain.repository.login.AuthRepository
import org.ssg_tab.presentation.ui.login.KakaoLoginManager
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val kakaoLoginManager: KakaoLoginManager,
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val isLoggingIn = AtomicBoolean(false)

    fun signInWithKakao(
        context: Context,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        if (isLoggingIn.getAndSet(true)) {
            return
        }

        kakaoLoginManager.login(context) { result ->
            result.onSuccess { kakaoToken ->
                loginToServer(kakaoToken, onSuccess, onFailure)
            }.onFailure { error ->
                onFailure(error)
                isLoggingIn.set(false)
            }
        }
    }

    private fun loginToServer(
        kakaoToken: OAuthToken,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        Log.d(TAG, "loginToServer() called")
        viewModelScope.launch {

            Log.d(TAG, "Creating SignInRequestDto with kakao token: ${kakaoToken.accessToken}")

            val signInRequest = SignInRequestDto(
                kakaoAccessToken = kakaoToken.accessToken
            )

            Log.d(TAG, "SignInRequestDto created:")
            Log.d(TAG, "- kakaoAccessToken: ${signInRequest.kakaoAccessToken}")
            Log.d(TAG, "- SignInRequestDto toString(): $signInRequest")

            try {
                Log.d(TAG, "About to call authRepository.signIn()")
                Log.d(TAG, "Request object details:")
                Log.d(TAG, "  - Class: ${signInRequest::class.java.name}")
                Log.d(TAG, "  - Hash: ${signInRequest.hashCode()}")

                authRepository.signIn(signInRequestDto = signInRequest)
                    .onSuccess { loginResult ->
                        Log.d(TAG, "Server login SUCCESS")
                        Log.d(TAG, "Login result:")
                        Log.d(TAG, "- step: ${loginResult.step}")
                        Log.d(TAG, "- accessToken: ${loginResult.accessToken}")
                        Log.d(TAG, "- refreshToken: ${loginResult.refreshToken}")

                        Timber.d("kakao AccessToken: ${loginResult.accessToken}")

                        tokenManager.saveAccessToken(loginResult.accessToken)
                        tokenManager.saveRefreshToken(loginResult.refreshToken)
                        Log.d(TAG, "Tokens saved to TokenManager")

                        val needSignUp = loginResult.step == "ONBOARDING"
                        Log.d(TAG, "needSignUp: $needSignUp (step: ${loginResult.step})")

                        onSuccess(needSignUp)
                        isLoggingIn.set(false)
                        Log.d(TAG, "Login process completed successfully")
                    }
                    .onFailure { error ->
                        Log.e(TAG, "Server login FAILED")
                        Log.e(TAG, "Error type: ${error::class.java.name}")
                        Log.e(TAG, "Error message: ${error.message}")
                        Log.e(TAG, "Error cause: ${error.cause}")

                        if (error is retrofit2.HttpException) {
                            Log.e(TAG, "HTTP Error Code: ${error.code()}")
                            Log.e(TAG, "HTTP Error Response: ${error.response()}")
                            try {
                                val errorBody = error.response()?.errorBody()?.string()
                                Log.e(TAG, "HTTP Error Body: $errorBody")
                            } catch (e: Exception) {
                                Log.e(TAG, "Failed to read error body: ${e.message}")
                            }
                        }

                        Timber.e("Login failed: ${error.message}")
                        onFailure(error)
                        isLoggingIn.set(false)
                        Log.d(TAG, "Login process completed with failure")
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Unexpected exception in loginToServer", e)
                onFailure(e)
                isLoggingIn.set(false)
            }
        }
    }
}