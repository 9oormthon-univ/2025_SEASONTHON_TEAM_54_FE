package org.ssg_tab.presentation.ui.login

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.login.component.Tutorial1Screen
import org.ssg_tab.presentation.ui.login.component.Tutorial2Screen
import org.ssg_tab.presentation.ui.login.component.Tutorial3Screen
import kotlin.math.absoluteValue

@Composable
fun TutorialScreen(
    onLoginSuccess: (needSignUp: Boolean) -> Unit,
) {
    val pagerState = rememberPagerState { 4 }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
    ) {
        AnimatedBackground(pagerState = pagerState)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                pageSpacing = 16.dp,
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) { page ->
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                        ).absoluteValue

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleX = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleY = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                            rotationY = lerp(
                                start = 0f,
                                stop = 30f,
                                fraction = pageOffset.coerceIn(-1f, 1f)
                            )
                        }
                ) {
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
}

@Composable
fun AnimatedBackground(pagerState: PagerState) {
    val backgroundColors = listOf(
        listOf(Color.Transparent, Color(0xFFE8F4FD)), // Tutorial1
        listOf(Color.Transparent, Color(0xFFC3E3FF)), // Tutorial2
        listOf(Color.Transparent, Color(0xFFB8DAFF)), // Tutorial3
        listOf(Color.Transparent, Color(0xFFA8D0FF))  // Login
    )

    val currentColors by remember {
        derivedStateOf {
            val page = pagerState.currentPage
            val nextPage = (page + 1).coerceAtMost(backgroundColors.size - 1)
            val progress = pagerState.currentPageOffsetFraction

            if (progress == 0f) {
                backgroundColors[page]
            } else {
                val currentColor = backgroundColors[page][1]
                val nextColor = backgroundColors[nextPage][1]

                val blendedColor = Color(
                    red = lerp(currentColor.red, nextColor.red, progress),
                    green = lerp(currentColor.green, nextColor.green, progress),
                    blue = lerp(currentColor.blue, nextColor.blue, progress),
                    alpha = lerp(currentColor.alpha, nextColor.alpha, progress)
                )

                listOf(Color.Transparent, blendedColor)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(
                Brush.verticalGradient(colors = currentColors)
            )
    ) {
        // 추가적인 파티클 효과나 장식 요소들을 여기에 추가할 수 있습니다
        FloatingParticles(pagerState = pagerState)
    }
}

@Composable
fun FloatingParticles(pagerState: PagerState) {
    val infiniteTransition = rememberInfiniteTransition(label = "particles")

    val particle1Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "particle1"
    )

    val particle2Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -80f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "particle2"
    )

    val currentPage = pagerState.currentPage
    val particleAlpha = if (currentPage < 3) 0.3f else 0f

    Box(modifier = Modifier.fillMaxSize()) {
        // 파티클 1
        Box(
            modifier = Modifier
                .size(8.dp)
                .offset(x = 50.dp + particle1Offset.dp, y = 100.dp)
                .alpha(particleAlpha)
                .background(
                    Color.White.copy(alpha = 0.6f),
                    CircleShape
                )
        )

        // 파티클 2
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(x = 250.dp + particle2Offset.dp, y = 150.dp)
                .alpha(particleAlpha)
                .background(
                    Color.White.copy(alpha = 0.4f),
                    CircleShape
                )
        )

        // 파티클 3
        Box(
            modifier = Modifier
                .size(4.dp)
                .offset(x = 150.dp - particle1Offset.dp, y = 200.dp)
                .alpha(particleAlpha)
                .background(
                    Color.White.copy(alpha = 0.5f),
                    CircleShape
                )
        )
    }
}

@Composable
fun PageIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pagerState.pageCount) { page ->
            val isSelected = page == pagerState.currentPage

            val width by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 8.dp,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorWidth"
            )

            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0.4f,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorAlpha"
            )

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(width)
                    .alpha(alpha)
                    .background(
                        if (isSelected) SsgTabTheme.colors.MainBlue else SsgTabTheme.colors.LightGray,
                        CircleShape
                    )
            )
        }
    }
}

// lerp 함수 정의 (필요한 경우)
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}