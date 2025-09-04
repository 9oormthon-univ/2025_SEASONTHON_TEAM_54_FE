package org.ssg_tab.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.home.HomeScreen

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavigation(
    paddingValues: PaddingValues,
    NavController: NavController,
    snackbarHostState: SnackbarHostState
) {
    composable<Home> {
        HomeScreen(
            paddingValues = paddingValues,
            navController = NavController,
            modifier = Modifier
        )
    }
}

@Serializable
data object Home : MainTabRoute