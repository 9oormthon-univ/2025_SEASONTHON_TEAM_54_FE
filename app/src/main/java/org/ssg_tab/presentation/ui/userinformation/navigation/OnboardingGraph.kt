package org.ssg_tab.presentation.ui.userinformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.ssg_tab.presentation.ui.home.navigation.navigateToHome
import org.ssg_tab.presentation.ui.userinformation.SelectAgeHomeScreen
import org.ssg_tab.presentation.ui.userinformation.SelectCategoryScreen
import org.ssg_tab.presentation.ui.userinformation.SelectJobScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    // 1단계: 관심사 선택
    composable<SelectCategory> {
        SelectCategoryScreen(
            onNextClick = { navController.navigate(SelectAgeHome) }
        )
    }

    // 2단계: 연령대 및 사는 지역 선택
    composable<SelectAgeHome> {
        SelectAgeHomeScreen(
            onNavigateBack = { navController.navigateUp() },
            onNextClick = { navController.navigate(SelectJob) }
        )
    }

    // 3단계: 직업 선택
    composable<SelectJob> {
        SelectJobScreen(
            onNavigateBack = { navController.navigateUp() },
            onNextClick = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(SelectCategory) {
                            inclusive = true
                        }
                    }
                )
            }
        )
    }
}