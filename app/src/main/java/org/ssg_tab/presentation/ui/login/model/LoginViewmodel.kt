package org.ssg_tab.presentation.ui.login.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.domain.repository.login.AuthRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun signInWithKakao(
        context: Context,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Timber.e(error, "카카오톡 로그인 실패")
                    loginWithKakaoAccount(context, onSuccess, onFailure)
                } else if (token != null) {
                    processKakaoLogin(token, onSuccess, onFailure)
                }
            }
        } else {
            loginWithKakaoAccount(context, onSuccess, onFailure)
        }
    }

    private fun loginWithKakaoAccount(
        context: Context,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                Timber.e(error, "카카오 계정 로그인 실패")
                onFailure(error)
            } else if (token != null) {
                processKakaoLogin(token, onSuccess, onFailure)
            }
        }
    }

    private fun processKakaoLogin(
        token: OAuthToken,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        viewModelScope.launch {
            authRepository.signIn(token.accessToken, "kakao").fold(
                onSuccess = { result ->
                    onSuccess(false)
                    tokenManager.saveToken(result.accessToken)
                    tokenManager.saveRefreshToken(result.refreshToken)
                },
                onFailure = {
                    Timber.e(it, "서버 로그인 실패")
                    onFailure(it)
                }
            )
        }
    }
}