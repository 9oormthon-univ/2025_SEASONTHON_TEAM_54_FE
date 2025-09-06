package org.ssg_tab.presentation.ui.login.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import kotlinx.serialization.Serializable
import org.ssg_tab.presentation.ui.login.TutorialScreen
import org.ssg_tab.presentation.ui.userinformation.navigation.OnboardingRoute

@Serializable
object Tutorial

fun NavController.navigateToTutorial(navOptions: NavOptions? = null) {
    this.navigate(Tutorial, navOptions)
}

fun NavGraphBuilder.tutorialGraph(navController: NavController) {
    composable<Tutorial> {
        TutorialScreen(
            onLoginSuccess = {
                navController.navigateToOnboarding(
                    navOptions = navOptions {
                        popUpTo(Tutorial) { inclusive = true }
                    }
                )
            }
        )
    }
}

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    this.navigate(OnboardingRoute, navOptions)
}