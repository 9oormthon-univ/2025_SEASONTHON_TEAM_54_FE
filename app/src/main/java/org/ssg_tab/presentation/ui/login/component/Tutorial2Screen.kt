package org.ssg_tab.presentation.ui.login.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun Tutorial2Screen() {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        AnimatedIconFromBottom(
            iconRes = R.drawable.ic_tutorial_2,
            isVisible = isVisible,
            delay = 0
        )

        Spacer(modifier = Modifier.height(60.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 800, delayMillis = 600)
            ) + slideInVertically(
                animationSpec = tween(durationMillis = 800, delayMillis = 600),
                initialOffsetY = { it / 4 }
            )
        ) {
            Text(
                text = "부담 없이 한 입씩\n쉽고 가볍게, 슥",
                style = SsgTabTheme.typography.Large_R,
                color = SsgTabTheme.colors.DarkGray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        SsgTabTheme {
            AnimatedStackedCardsEffect(isVisible = isVisible)
        }
    }
}

@Composable
fun AnimatedIconFromBottom(
    iconRes: Int,
    isVisible: Boolean,
    delay: Int = 0
) {
    val offsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else 200f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = 0.1f
        ).delayed(delay),
        label = "iconOffsetY"
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
            .offset(y = offsetY.dp)
            .alpha(alpha)
    )
}

@Composable
fun AnimatedStackedCardsEffect(isVisible: Boolean) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AnimatedSingleCard(offsetY = 90.dp, isVisible = isVisible, delay = 1000, gradientAlpha = 0.3f)
        AnimatedSingleCard(offsetY = 60.dp, isVisible = isVisible, delay = 1200, gradientAlpha = 0.5f)
        AnimatedSingleCard(offsetY = 30.dp, isVisible = isVisible, delay = 1400, gradientAlpha = 0.7f)
        AnimatedSingleCard(offsetY = 0.dp, isVisible = isVisible, delay = 1600, gradientAlpha = 1f)
    }
}

@Composable
private fun AnimatedSingleCard(
    offsetY: Dp,
    isVisible: Boolean,
    delay: Int,
    gradientAlpha: Float
) {
    val cardOffsetY by animateFloatAsState(
        targetValue = if (isVisible) offsetY.value else offsetY.value + 100f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "cardOffsetY"
    )

    val cardAlpha by animateFloatAsState(
        targetValue = if (isVisible) gradientAlpha else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = delay),
        label = "cardAlpha"
    )

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring<Float>(
            dampingRatio = 0.7f,
            stiffness = 200f
        ).delayed(800),
        label = "cardScale"
    )

    Card(
        modifier = Modifier
            .offset(y = cardOffsetY.dp)
            .alpha(cardAlpha)
            .width(300.dp)
            .height(400.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(36.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_3_blue),
            contentDescription = "tutorial_card",
            tint = Color.Unspecified,
        )
    }
}

private fun <T> AnimationSpec<T>.delayed(delayMillis: Int): AnimationSpec<T> {
    return tween<T>(
        durationMillis = if (this is TweenSpec) this.durationMillis else 800,
        delayMillis = delayMillis
    )
}

@Preview(showBackground = true)
@Composable
private fun Tutorial2ScreenPreview() {
    SsgTabTheme {
        Tutorial2Screen()
    }
}