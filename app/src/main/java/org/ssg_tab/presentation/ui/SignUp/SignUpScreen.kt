package org.ssg_tab.presentation.ui.SignUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTextField
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme


@Composable
fun SignUpScreen() {

    var email by remember { mutableStateOf("") }

    val isButtonEnabled = email.isNotBlank()

    SignUpContent(
        email = email,
        onEmailChange = { newEmail -> email = newEmail },
        isButtonEnabled = isButtonEnabled,
        onSignUpClick = {
            // TODO: 회원가입 버튼 클릭 시 실행할 로직 (ViewModel 호출 등)
        }
    )
}

@Composable
fun SignUpContent(
    email: String,
    onEmailChange: (String) -> Unit,
    isButtonEnabled: Boolean,
    onSignUpClick: () -> Unit
) {
    Scaffold(
        containerColor = SsgTabTheme.colors.White,

        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                middleText = "",
                rightIcon = null
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // 당신의 첫 슥텝을 응원합니다!
            Text(
                color = SsgTabTheme.colors.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 34.sp,

                text = buildAnnotatedString {
                    append("당신의 첫 ")

                    withStyle(style = SpanStyle(color = SsgTabTheme.colors.MainBlue)) {
                        append("슥텝")
                    }

                    append("을 \n응원합니다!")
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 1분이면 회원가입 가능해요
            Text(
                text = "1분이면 회원가입 가능해요",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 12.sp,
                color = SsgTabTheme.colors.SoftGray,
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 이메일 입력
            Text(
                text = "이메일",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(8.dp))


            SsgTabTextField(
                textValue = email,
                onTextChanged = onEmailChange,
                placeHolder = "이메일을 입력해주세요",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(50.dp))


            // 버튼 화면 하단에 고정
            Spacer(modifier = Modifier.weight(1f))

            SsgTabButton(
                name = "다음",
                isEnabled = isButtonEnabled,
                onClick = onSignUpClick,
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

// 미리보기
@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SsgTabTheme {
        SignUpContent(
            email = "",
            onEmailChange = {},
            isButtonEnabled = false,
            onSignUpClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenFilledPreview() {
    SsgTabTheme {
        SignUpContent(
            email = "ssg-tab@example.com",
            onEmailChange = {},
            isButtonEnabled = true,
            onSignUpClick = {}
        )
    }
}