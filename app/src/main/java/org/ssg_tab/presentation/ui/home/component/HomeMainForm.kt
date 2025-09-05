package org.ssg_tab.presentation.ui.home.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import kotlin.math.abs

data class NewsItem(
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val source: String,
    val imageUrl: String,
)

@Preview(showBackground = true)
@Composable
private fun PreviewHomeMainForm() {
    SsgTabTheme {
        HomeMainForm(
            newsItems = listOf(
                NewsItem(
                    id = 1,
                    title = "첫 번째 뉴스: 2025년 국가직 9급 채용",
                    content = "첫 번째 내용: 2025년 국가직 9급 공채가 진행됩니다. 총 595명을 선발할 예정입니다.",
                    category = "공직",
                    source = "시사비즈",
                    imageUrl = "https://picsum.photos/400/300?random=1"
                ),
                NewsItem(
                    id = 2,
                    title = "두 번째 뉴스: 경제 동향 분석",
                    content = "두 번째 내용: 올해 경제 성장률이 예상보다 높게 나타났습니다.",
                    category = "경제",
                    source = "경제신문",
                    imageUrl = "https://picsum.photos/400/300?random=2"
                ),
                NewsItem(
                    id = 3,
                    title = "세 번째 뉴스: 기술 혁신 소식",
                    content = "세 번째 내용: AI 기술 발전으로 다양한 분야에서 변화가 예상됩니다.",
                    category = "기술",
                    source = "테크리뷰",
                    imageUrl = "https://picsum.photos/400/300?random=3"
                ),
                NewsItem(
                    id = 4,
                    title = "네 번째 뉴스: 교육 정책 변화",
                    content = "네 번째 내용: 새로운 교육 정책이 발표되어 학생들에게 도움이 될 것으로 보입니다.",
                    category = "교육",
                    source = "교육일보",
                    imageUrl = "https://picsum.photos/400/300?random=4"
                ),
                NewsItem(
                    id = 5,
                    title = "다섯 번째 뉴스: 환경 보호 캠페인",
                    content = "다섯 번째 내용: 지구 환경 보호를 위한 새로운 캠페인이 시작되었습니다.",
                    category = "환경",
                    source = "그린뉴스",
                    imageUrl = "https://picsum.photos/400/300?random=5"
                )
            ),
            navController = rememberNavController(),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun HomeMainForm(
    newsItems: List<NewsItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    var currentIndex by remember { mutableIntStateOf(0) }
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }

    val dragThreshold = with(density) { 100.dp.toPx() }

    val animatedOffset by animateFloatAsState(
        targetValue = dragOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        finishedListener = {
            if (isAnimating) {
                isAnimating = false
                dragOffset = 0f
            }
        }
    )

    val getCurrentCard = { index: Int ->
        if (newsItems.isEmpty()) null
        else newsItems[((index % newsItems.size) + newsItems.size) % newsItems.size]
    }

    val currentCard = getCurrentCard(currentIndex)
    val nextCard = if (dragOffset > 0) {
        getCurrentCard(currentIndex - 1)
    } else if (dragOffset < 0) {
        getCurrentCard(currentIndex + 1)
    } else {
        null
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        BackgroundCards()

        if (abs(dragOffset) > 10f && nextCard != null) {
            SwipeCard(
                newsItem = nextCard,
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        val progress =
                            (abs(animatedOffset) / with(density) { 200.dp.toPx() }).coerceIn(0f, 1f)
                        translationY = if (dragOffset > 0) {
                            with(density) { 70.dp.toPx() } * (1f - progress)
                        } else {
                            -with(density) { 80.dp.toPx() } * (1f - progress)
                        }
                        alpha = progress * 0.7f

                        val scale = 0.96f + (progress * 0.04f)
                        scaleX = scale
                        scaleY = scale
                    }
            )
        }

        if (currentCard != null) {
            SwipeCard(
                newsItem = currentCard,
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        translationY = animatedOffset
                        val progress =
                            (abs(animatedOffset) / with(density) { 200.dp.toPx() }).coerceIn(0f, 1f)
                        alpha = 1f - (progress * 0.3f)

                        rotationZ = (animatedOffset / with(density) { 500.dp.toPx() }) * 1.5f
                        val scaleFactor = 1f - (progress * 0.02f)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onDragEnd = {
                                when {
                                    dragOffset > dragThreshold -> {
                                        currentIndex =
                                            (currentIndex - 1 + newsItems.size) % newsItems.size
                                        dragOffset = 0f
                                        isAnimating = true
                                    }

                                    dragOffset < -dragThreshold -> {
                                        currentIndex = (currentIndex + 1) % newsItems.size
                                        dragOffset = 0f
                                        isAnimating = true
                                    }

                                    else -> {
                                        dragOffset = 0f
                                        isAnimating = true
                                    }
                                }
                            }
                        ) { _, dragAmount ->
                            if (!isAnimating) {
                                dragOffset = (dragOffset + dragAmount).coerceIn(
                                    -with(density) { 200.dp.toPx() },
                                    with(density) { 200.dp.toPx() }
                                )
                            }
                        }
                    }
            )
        }
    }
}

@Composable
private fun BackgroundCards(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(horizontal = 16.dp)
            .graphicsLayer {
                rotationZ = -8F
            }
            .shadow(
                elevation = 8.dp,
                ambientColor = SsgTabTheme.colors.MidGray,
                spotColor = SsgTabTheme.colors.MidGray,
                shape = RoundedCornerShape(30.dp),
            )
            .background(
                Color.White,
                RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(horizontal = 16.dp)
            .graphicsLayer {
                rotationZ = 8F
            }
            .shadow(
                elevation = 12.dp,
                ambientColor = SsgTabTheme.colors.MidGray,
                spotColor = SsgTabTheme.colors.MidGray,
                shape = RoundedCornerShape(30.dp),
            )
            .background(
                Color.White,
                RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
    )
}

@Composable
private fun SwipeCard(
    newsItem: NewsItem,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 16.dp,
                ambientColor = SsgTabTheme.colors.MidGray,
                spotColor = SsgTabTheme.colors.MidGray,
                shape = RoundedCornerShape(30.dp),
            )
            .background(
                SsgTabTheme.colors.White,
                RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                AsyncImage(
                    model = newsItem.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.8f)
                                )
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(y = 12.dp)
                        .padding(start = 20.dp)
                        .background(
                            color = SsgTabTheme.colors.LightBlue,
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = newsItem.category,
                        color = Color.White,
                        style = SsgTabTheme.typography.Small_Sb,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = newsItem.title,
                    color = Color.White,
                    style = SsgTabTheme.typography.Large_Sb,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 26.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                        .padding(bottom = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = newsItem.content,
                    color = SsgTabTheme.colors.DarkGray,
                    style = SsgTabTheme.typography.Small_R,
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    maxLines = 8
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "출처",
                            color = SsgTabTheme.colors.SoftGray,
                            style = SsgTabTheme.typography.Small_R,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "•",
                            color = SsgTabTheme.colors.SoftGray,
                            style = SsgTabTheme.typography.Small_R,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = newsItem.source,
                            color = SsgTabTheme.colors.SoftGray,
                            style = SsgTabTheme.typography.Small_Sb,
                            fontSize = 13.sp
                        )
                    }

                }
            }
        }
    }
}