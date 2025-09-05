package org.ssg_tab.presentation.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.ssg_tab.presentation.ui.guide.navigation.guideNavigation
import org.ssg_tab.presentation.ui.home.navigation.homeNavigation
import org.ssg_tab.presentation.ui.login.navigation.loginNavigation
import org.ssg_tab.presentation.ui.mypage.navigation.mypageNavigation
import org.ssg_tab.presentation.ui.storage.navigation.storageNavigation
import org.ssg_tab.presentation.ui.userinformation.navigation.onboardingGraph


@Composable
fun SSGNavHost(
    navigator: MainNavigator,
    startDestination: Any,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        homeNavigation(
            paddingValues = paddingValues,
            NavController = navigator.navController,
            snackbarHostState = snackbarHostState
        )

        storageNavigation(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState
        )

        guideNavigation(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState
        )

        mypageNavigation(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            navController = navigator.navController,
            )

        loginNavigation(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState
        )

        onboardingGraph(navigator.navController)

    }
}