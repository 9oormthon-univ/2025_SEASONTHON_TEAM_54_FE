package org.ssg_tab.presentation.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.ssg_tab.presentation.ui.guide.navigation.guideNavigation
import org.ssg_tab.presentation.ui.home.navigation.homeNavigation
import org.ssg_tab.presentation.ui.mypage.navigation.mypageNavigation
import org.ssg_tab.presentation.ui.storage.navigation.storageNavigation


@Composable
fun SSGNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        homeNavigation(
            paddingValues = paddingValues,
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
            snackbarHostState = snackbarHostState
        )
    }
}