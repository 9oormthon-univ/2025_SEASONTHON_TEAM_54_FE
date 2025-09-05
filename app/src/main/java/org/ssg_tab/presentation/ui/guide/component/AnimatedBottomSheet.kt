package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewAnimatedBottomSheet() {
    SsgTabTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SsgTabTheme.colors.WhiteGray)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            AnimatedBottomSheet(
                title = "위로 끌어올려\n다음 퀴즈 풀기",
                expandedContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "퀴즈 화면",
                            style = SsgTabTheme.typography.Large_Sb,
                            color = SsgTabTheme.colors.Black
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "여기에 퀴즈 내용이 들어갑니다.",
                            style = SsgTabTheme.typography.Regular_R,
                            color = SsgTabTheme.colors.LightGray
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun AnimatedBottomSheet(
    title: String,
    expandedContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }

    var offsetY by remember { mutableFloatStateOf(0f) }
    var isExpanded by remember { mutableStateOf(false) }

    val animatedOffsetY by animateFloatAsState(
        targetValue = if (isExpanded) -screenHeight * 0f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "bottomSheetOffset"
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0f,
        animationSpec = tween(300),
        label = "expandedContentAlpha"
    )

    val collapsedAlpha by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 1f,
        animationSpec = tween(300),
        label = "collapsedContentAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isExpanded) configuration.screenHeightDp.dp * 0.85f else 200.dp)
            .graphicsLayer {
                translationY = animatedOffsetY + offsetY
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        val threshold = -screenHeight * 0.3f
                        if (offsetY < threshold) {
                            isExpanded = true
                        } else {
                            isExpanded = false
                        }
                        offsetY = 0f
                    }
                ) { _, dragAmount ->
                    val newOffset = offsetY + dragAmount.y
                    if (newOffset <= 0f) {
                        offsetY = newOffset
                    }
                }
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
                .background(
                    color = SsgTabTheme.colors.White,
                    shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
        )

        if (!isExpanded || collapsedAlpha > 0.1f) {
            CollapsedContent(
                title = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(collapsedAlpha)
            )
        }

        if (isExpanded || animatedAlpha > 0.1f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(animatedAlpha)
            ) {
                expandedContent()
            }
        }
    }
}

@Composable
private fun CollapsedContent(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = SsgTabTheme.typography.Large_Sb,
            color = SsgTabTheme.colors.Black,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp
        )
    }
}