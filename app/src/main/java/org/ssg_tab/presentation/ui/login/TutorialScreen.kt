package org.ssg_tab.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.login.component.Tutorial1Screen
import org.ssg_tab.presentation.ui.login.component.Tutorial2Screen
import org.ssg_tab.presentation.ui.login.component.Tutorial3Screen

@Composable
fun TutorialScreen(
    onLoginSuccess: (needSignUp: Boolean) -> Unit
) {
    val pagerState = rememberPagerState { 4 }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
    ) {
        // 배경 그라데이션
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xFFC3E3FF))
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> Tutorial1Screen()
                    1 -> Tutorial2Screen()
                    2 -> Tutorial3Screen()
                    3 -> LoginScreen(onLoginSuccess = onLoginSuccess)
                }
            }
        }
    }
}


