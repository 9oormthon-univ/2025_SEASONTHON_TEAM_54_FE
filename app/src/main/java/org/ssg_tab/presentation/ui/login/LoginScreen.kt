package org.ssg_tab.presentation.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SsgTabTheme.colors.MainBlue,
                        SsgTabTheme.colors.SubBlue
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_1),
            contentDescription = "tutorial_step1",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .graphicsLayer {
                    rotationZ = 180F
                }
        )

        Spacer(modifier = Modifier.height(150.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_logo_text),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Yellow,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .noRippleClickable {
                        viewModel.signInWithKakao(
                            context = context,
                            onSuccess = { needSignUp ->
                                onLoginSuccess(needSignUp)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_kakao),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "카카오 로그인",
                        color = Color(0xFF3C1E1E),
                        style = SsgTabTheme.typography.Regular_Sb
                        )

                }

            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = SsgTabTheme.colors.White,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .padding(vertical = 18.dp, horizontal = 16.dp)

            ) {
                Text(
                    text = "이메일로 시작하기",
                    color = SsgTabTheme.colors.TextBlue,
                    style = SsgTabTheme.typography.Regular_Sb
                )
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))

    }
}