package org.ssg_tab.presentation.ui.login.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.ssg_tab.core.network.TokenManager
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
        viewModelScope.launch {
            authRepository.signIn(kakaoToken.accessToken, "kakao")
                .onSuccess { loginResult ->
                    Timber.d("kakao AccessToken: ${loginResult.accessToken}")

                    tokenManager.saveAccessToken(loginResult.accessToken)
                    tokenManager.saveRefreshToken(loginResult.refreshToken)
                    onSuccess(true)
                    isLoggingIn.set(false)
                }
                .onFailure { error ->
                    onFailure(error)
                    isLoggingIn.set(false)
                }
        }
    }
}