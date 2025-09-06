package org.ssg_tab.presentation.ui.login.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun Tutorial3Screen() {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300) // 화면 진입 후 살짝 딜레이
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        AnimatedIconFromLeft(
            iconRes = R.drawable.ic_tutorial_3,
            isVisible = isVisible,
            delay = 0
        )

        Spacer(modifier = Modifier.height(60.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 800, delayMillis = 600)
            ) + slideInHorizontally(
                animationSpec = tween(durationMillis = 800, delayMillis = 600),
                initialOffsetX = { -it / 2 }
            )
        ) {
            Text(
                text = "보고 싶을 땐 언제든\n필요한 순간, 탭",
                style = SsgTabTheme.typography.Large_R,
                color = SsgTabTheme.colors.DarkGray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        AnimatedCard(isVisible = isVisible)

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedGradientIcons(isVisible = isVisible)
    }
}

@Composable
fun AnimatedIconFromLeft(
    iconRes: Int,
    isVisible: Boolean,
    delay: Int = 0
) {
    val offsetX by animateFloatAsState(
        targetValue = if (isVisible) 0f else -300f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "iconOffsetX"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 800, delayMillis = delay),
        label = "iconAlpha"
    )

    Icon(
        imageVector = ImageVector.vectorResource(id = iconRes),
        contentDescription = "tutorial_icon",
        tint = Color.Unspecified,
        modifier = Modifier
            .offset(x = offsetX.dp)
            .alpha(alpha)
    )
}

@Composable
fun AnimatedCard(isVisible: Boolean) {
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.7f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "cardScale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = 800),
        label = "cardAlpha"
    )

    val offsetX by animateFloatAsState(
        targetValue = if (isVisible) 0f else 200f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "cardOffsetX"
    )

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(280.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .alpha(alpha)
            .offset(x = offsetX.dp),
        shape = RoundedCornerShape(36.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

    }
}

@Composable
fun AnimatedGradientIcons(isVisible: Boolean) {
    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 0.dp),
        contentAlignment = Alignment.Center,
    ) {
        GradientTrailIcon(
            iconRes = R.drawable.ic_tutorial_3_white,
            isVisible = isVisible,
            delay = 1400,
            offsetX = -60f,
            alpha = 0.3f
        )

        GradientTrailIcon(
            iconRes = R.drawable.ic_tutorial_3_white,
            isVisible = isVisible,
            delay = 1300,
            offsetX = -40f,
            alpha = 0.5f
        )

        GradientTrailIcon(
            iconRes = R.drawable.ic_tutorial_3_white,
            isVisible = isVisible,
            delay = 1200,
            offsetX = -20f,
            alpha = 0.7f
        )

        GradientTrailIcon(
            iconRes = R.drawable.ic_tutorial_3_blue,
            isVisible = isVisible,
            delay = 1600,
            offsetX = 0f,
            alpha = 1f
        )
    }
}

@Composable
fun GradientTrailIcon(
    iconRes: Int,
    isVisible: Boolean,
    delay: Int,
    offsetX: Float,
    alpha: Float
) {
    val animatedOffsetX by animateFloatAsState(
        targetValue = if (isVisible) offsetX else offsetX - 100f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "trailOffsetX"
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isVisible) alpha else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = delay),
        label = "trailAlpha"
    )

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "trailScale"
    )

    Icon(
        imageVector = ImageVector.vectorResource(id = iconRes),
        contentDescription = "gradient_trail_icon",
        tint = Color.Unspecified,
        modifier = Modifier
            .offset(x = animatedOffsetX.dp)
            .alpha(animatedAlpha)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    )
}

private fun <T> AnimationSpec<T>.delayed(delayMillis: Int): AnimationSpec<T> {
    return tween<T>(
        durationMillis = when (this) {
            is TweenSpec -> this.durationMillis
            is SpringSpec -> 800
            else -> 800
        },
        delayMillis = delayMillis
    )
}
@Preview(showBackground = true)
@Composable
private fun Tutorial3ScreenPreview() {
    SsgTabTheme {
        Tutorial3Screen()
    }
}