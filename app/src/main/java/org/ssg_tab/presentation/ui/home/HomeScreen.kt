package org.ssg_tab.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.domain.model.entity.home.toNewsItemList
import org.ssg_tab.presentation.ui.home.component.HomeActionButton
import org.ssg_tab.presentation.ui.home.component.HomeCategory
import org.ssg_tab.presentation.ui.home.component.HomeMainForm
import org.ssg_tab.presentation.ui.home.component.HomeTopBar
import org.ssg_tab.presentation.ui.home.component.NewsItem
import org.ssg_tab.presentation.ui.home.model.HomeViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SsgTabTheme {
        HomeScreen(
            paddingValues = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }
        }
    }

    when {
        state.isLoading -> {
            LoadingScreen()
        }

        state.error != null -> {
            ErrorScreen(
                error = state.error!!,
                onRetry = { viewModel.retry() }
            )
        }

        else -> {
            val newsItems = state.homeFeed?.toNewsItemList() ?: getDefaultNewsItems()

            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .nestedScroll(nestedScrollConnection)
                    .background(color = SsgTabTheme.colors.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeTopBar(onClick = {})
                HomeCategory(modifier = Modifier)
                HomeMainForm(
                    newsItems = newsItems,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    navController = navController
                )
                HomeActionButton(onClick = {})
                Spacer(modifier = Modifier.padding(14.dp))
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    error: String,
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "오류가 발생했습니다")
            Text(text = error)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

private fun getDefaultNewsItems(): List<NewsItem> {
    return listOf(
        NewsItem(
            id = 1,
            title = "첫 번째 뉴스: 2025년 국가직 9급 8월 일정! 595명 최종 배치",
            content = "첫 번째 내용: 2025년 국가직 9급 공채 최종 합격자의 일정이 8월 13일 부터...",
            category = "공직",
            source = "시사비즈",
            imageUrl = "https://picsum.photos/400/300?random=1"
        ),
        NewsItem(
            id = 2,
            title = "두 번째 뉴스: 2번째 숏폼잉",
            content = "두 번째 내용: 025년 국가다고 시청하이고 총 595명이 최종 선발됐는데...",
            category = "취업",
            source = "시사비즈",
            imageUrl = "https://picsum.photos/400/300?random=2"
        ),
        NewsItem(
            id = 3,
            title = "세 번째 뉴스: 3번째 숏폼잉",
            content = "세 번째 내용: 으아아아아아아아아ㅏ 발됐는데, 행정직 339명...",
            category = "취업",
            source = "시사비즈",
            imageUrl = "https://picsum.photos/400/300?random=3"
        )
    )
}