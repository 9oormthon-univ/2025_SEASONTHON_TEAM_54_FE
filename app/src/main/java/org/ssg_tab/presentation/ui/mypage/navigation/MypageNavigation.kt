package org.ssg_tab.presentation.ui.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.mypage.MypageScreen

fun NavController.navigateToMypage(
    navOptions: NavOptions? = null
) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    composable<Mypage> {
        MypageScreen()
    }
}

@Serializable
data object Mypage : MainTabRoute