package org.ssg_tab.presentation.ui.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.mypage.MyHistoryScreen
import org.ssg_tab.presentation.ui.mypage.MypageScreen
import org.ssg_tab.presentation.ui.mypage.SetInterestingScreen

fun NavController.navigateToMypage(
    navOptions: NavOptions? = null
) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavigation(
    navController: NavController,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    composable<Mypage> {
        MypageScreen(
            onNavigateToSetInteresting = { navController.navigate(SetInteresting) },
            onNavigateToMyHistory = { navController.navigate(MyHistory) },
        )
    }
    composable<SetInteresting> {
        SetInterestingScreen(
            onNavigateBack = { navController.navigateUp() }
        )
    }

    composable<MyHistory> {
        MyHistoryScreen(
            onNavigateBack = { navController.navigateUp() }
        )
    }


}

@Serializable
data object Mypage : MainTabRoute

@Serializable
data object SetInteresting // 관심사 설정 화면

@Serializable
data object MyHistory // 나의 히스토리 화면
