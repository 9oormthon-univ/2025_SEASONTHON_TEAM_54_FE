package org.ssg_tab.presentation.ui.guide.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.guide.GuideScreen

fun NavController.navigateToGuide(
    navOptions: NavOptions? = null
) {
    navigate(Guide, navOptions)
}

fun NavGraphBuilder.guideNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    composable<Guide> {
        GuideScreen(
        )
    }
}

@Serializable
data object Guide : MainTabRoute