package org.ssg_tab.presentation.ui.userinformation.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.ssg_tab.presentation.ui.home.navigation.navigateToHome
import org.ssg_tab.presentation.ui.userinformation.SelectAgeHomeScreen
import org.ssg_tab.presentation.ui.userinformation.SelectCategoryScreen
import org.ssg_tab.presentation.ui.userinformation.SelectJobScreen
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingViewModel

fun NavGraphBuilder.onboardingGraph(navController: NavController) {

    // 1단계: 관심사 선택
    composable<SelectCategory> {
        val viewModel: OnboardingViewModel = hiltViewModel()

        SelectCategoryScreen(
            viewModel = viewModel,
            onNextClick = { navController.navigate(SelectAgeHome) }
        )
    }

    // 2단계: 연령대 및 사는 지역 선택
    composable<SelectAgeHome> { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(SelectCategory)
        }
        val viewModel: OnboardingViewModel = hiltViewModel(parentEntry)

        SelectAgeHomeScreen(
            viewModel = viewModel,
            onNavigateBack = { navController.navigateUp() },
            onNextClick = { navController.navigate(SelectJob) }
        )
    }

    // 3단계: 직업 선택
    composable<SelectJob> { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(SelectCategory)
        }
        val viewModel: OnboardingViewModel = hiltViewModel(parentEntry)

        SelectJobScreen(
            viewModel = viewModel,
            onNavigateBack = { navController.navigateUp() },
            onOnboardingComplete = {
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