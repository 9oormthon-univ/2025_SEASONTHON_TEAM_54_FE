package org.ssg_tab.presentation.ui.storage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.storage.GridItem
import org.ssg_tab.presentation.ui.storage.GridWeek
import org.ssg_tab.presentation.ui.storage.StorageScreen

fun NavController.navigateToStorage(
    navOptions: NavOptions? = null,
) {
    navigate(Storage, navOptions)
}

fun NavGraphBuilder.storageNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    composable<Storage> {
        StorageScreen(
            name = getMockGridItems(),
            time = getMockTimeItems()
        )
    }
}

// 임시 데이터 입니다
private fun getMockGridItems(): List<GridItem> {
    return listOf(
        GridItem(name = "삼성전자 주식 분석"),
        GridItem(name = "네이버 신입 채용"),
        GridItem(name = "강남 아파트 매매"),
        GridItem(name = "카카오 인턴십"),
        GridItem(name = "SK하이닉스 주가"),
        GridItem(name = "판교 원룸 전세"),
        GridItem(name = "LG화학 채용공고"),
        GridItem(name = "현대차 주식"),
        GridItem(name = "스타트업 취업"),
        GridItem(name = "서초구 오피스텔"),
        GridItem(name = "POSCO 신입"),
        GridItem(name = "셀트리온 바이오"),
        GridItem(name = "분당 신축 아파트"),
        GridItem(name = "넷플릭스 한국지사"),
        GridItem(name = "비트코인 투자"),
        GridItem(name = "테슬라 주식 전망"),
        GridItem(name = "구글 코리아 채용"),
        GridItem(name = "강서구 주택"),
        GridItem(name = "애플 신제품"),
        GridItem(name = "마이크로소프트"),
        GridItem(name = "부산 부동산"),
        GridItem(name = "스마트팜 투자"),
        GridItem(name = "메타버스 기업"),
        GridItem(name = "ESG 펀드"),
    )
}

private fun getMockTimeItems(): List<GridWeek> {
    return listOf(
        GridWeek(time = "1주차"),
        GridWeek(time = "2주차"),
        GridWeek(time = "3주차"),
        GridWeek(time = "4주차"),

        )
}

@Serializable
data object Storage : MainTabRoute