package org.ssg_tab.presentation.ui.home.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import kotlin.math.abs

@Preview(showBackground = true)
@Composable
private fun PreviewHomeMainForm() {
    HomeMainForm(
        jobPostings = List(5) {
            JobPosting(
                id = it,
                title = "Sample Job Title $it",
                company = "Sample Company $it",
                category = "Sample Category $it",
                location = "Sample Location $it",
                contractType = "Sample Contract Type $it",
                workHours = "Sample Work Hours $it",
                salary = "Sample Salary $it",
                jobType = "Sample Job Type $it",
                duties = "Sample Duties $it",
                recruitCount = "Sample Recruit Count $it",
                experience = "Sample Experience $it",
                education = "Sample Education $it",
                disabilityType = "Sample Disability Type $it",
            )
        },
        navController = rememberNavController(),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun HomeMainForm(
    jobPostings: List<JobPosting>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val density = LocalDensity.current

    var currentIndex by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }

    // 스와이프 임계값 (드래그 거리가 이 값보다 크면 카드가 넘어감) 일단 100으로 설정
    val dragThreshold = with(density) { 100.dp.toPx() }

    val animatedOffsetX by animateFloatAsState(
        targetValue = if (isAnimating) offsetX else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        finishedListener = {
            if (isAnimating) {
                isAnimating = false
                offsetX = 0f
            }
        }
    )
    val getCurrentCard = { index: Int ->
        if (jobPostings.isEmpty()) null
        else jobPostings[((index % jobPostings.size) + jobPostings.size) % jobPostings.size]
    }

    val currentCard = getCurrentCard(currentIndex)
    val nextCard = getCurrentCard(currentIndex + 1)
    val prevCard = getCurrentCard(currentIndex - 1)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        BackgroundCards(
            animationProgress = abs(animatedOffsetX) / with(density) { 300.dp.toPx() }
        )
        if (animatedOffsetX > 0 && prevCard != null) {
            SwipeCard(
                job = prevCard,
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = animatedOffsetX - with(density) { 300.dp.toPx() }
                        alpha = (animatedOffsetX / with(density) { 300.dp.toPx() }).coerceIn(0f, 1f)
                        scaleX = lerp(
                            0.8f,
                            1f,
                            (animatedOffsetX / with(density) { 300.dp.toPx() }).coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            0.8f,
                            1f,
                            (animatedOffsetX / with(density) { 300.dp.toPx() }).coerceIn(0f, 1f)
                        )
                    }
            )
        }
        if (animatedOffsetX < 0 && nextCard != null) {
            SwipeCard(
                job = nextCard,
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = animatedOffsetX + with(density) { 300.dp.toPx() }
                        alpha = (abs(animatedOffsetX) / with(density) { 300.dp.toPx() }).coerceIn(
                            0f,
                            1f
                        )
                        scaleX = lerp(
                            0.8f,
                            1f,
                            (abs(animatedOffsetX) / with(density) { 300.dp.toPx() }).coerceIn(
                                0f,
                                1f
                            )
                        )
                        scaleY = lerp(
                            0.8f,
                            1f,
                            (abs(animatedOffsetX) / with(density) { 300.dp.toPx() }).coerceIn(
                                0f,
                                1f
                            )
                        )
                    }
            )
        }

        if (currentCard != null) {
            SwipeCard(
                job = currentCard,
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = animatedOffsetX
                        rotationZ = (animatedOffsetX / with(density) { 300.dp.toPx() }) * 15f
                        alpha = 1f - (abs(animatedOffsetX) / with(density) { 300.dp.toPx() }) * 0.3f
                    }
                    .pointerInput(currentIndex) {
                        detectHorizontalDragGestures(
                            onDragEnd = {
                                when {
                                    offsetX > dragThreshold -> {
                                        currentIndex--
                                        offsetX = with(density) { 300.dp.toPx() }
                                        isAnimating = true
                                    }

                                    offsetX < -dragThreshold -> {
                                        currentIndex++
                                        offsetX = with(density) { -300.dp.toPx() }
                                        isAnimating = true
                                    }

                                    else -> {
                                        offsetX = 0f
                                        isAnimating = true
                                    }
                                }
                            }
                        ) { _, dragAmount ->
                            if (!isAnimating) {
                                offsetX = (offsetX + dragAmount).coerceIn(
                                    with(density) { -400.dp.toPx() },
                                    with(density) { 400.dp.toPx() }
                                )
                            }
                        }
                    },
            )
        }
    }
}

@Composable
private fun BackgroundCards(
    animationProgress: Float,
    modifier: Modifier = Modifier,
) {
    //이게 가장뒤에
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(375.dp)
            .offset(y = 36.dp)
            .scale(lerp(0.9f, 0.85f, animationProgress))
            .graphicsLayer {
                alpha = lerp(0.4f, 0.2f, animationProgress)
            }
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(30.dp),
                ambientColor = Color(0x1AABABAB),
                spotColor = Color(0x1AABABAB)
            )
            .background(
                SsgTabTheme.colors.LightGray,
                RoundedCornerShape(30.dp)
            )
    )

    // 요건 중간
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(433.dp)
            .padding(horizontal = 16.dp)
            .scale(lerp(0.95f, 0.9f, animationProgress))
            .graphicsLayer {
                alpha = lerp(0.6f, 0.4f, animationProgress)
            }
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0x1AABABAB),
                spotColor = Color(0x1AABABAB)
            )
            .background(
                Color(0xFFEBEBEB),
                RoundedCornerShape(24.dp)
            )
    )
}

@Composable
private fun SwipeCard(
    job: JobPosting,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
//            .wrapContentHeight()
            .height(600.dp)
            .padding(horizontal = 40.dp)
//            .border(
//                width = 1.dp,
//                color = SsgTabTheme.colors.LightGray,
//                shape = RoundedCornerShape(30.dp)
//            )
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(30.dp),
                ambientColor = Color(0x1AABABAB),
                spotColor = Color(0x1AABABAB)
            )
            .background(
                SsgTabTheme.colors.White,
                RoundedCornerShape(30.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        JobPostingCard(job = job, navController = navController)
    }
}