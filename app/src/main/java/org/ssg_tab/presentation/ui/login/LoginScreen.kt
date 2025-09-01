package org.ssg_tab.presentation.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.core.util.noRippleClickable
import org.ssg_tab.presentation.ui.login.model.LoginViewModel
import timber.log.Timber

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    LoginScreen(
        modifier = Modifier
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (needSignUp: Boolean) -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .noRippleClickable {
                    viewModel.signInWithKakao(
                        context = context,
                        onSuccess = {
                            onLoginSuccess
                            Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = { error ->
                            Timber.e(error, "로그인 실패")
                            Toast.makeText(
                                context,
                                "로그인에 실패했습니다. 다시 시도해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "카카오 로그인")
        }
    }
}