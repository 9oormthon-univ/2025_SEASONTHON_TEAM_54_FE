package org.ssg_tab.presentation.ui.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.Route
import org.ssg_tab.presentation.ui.login.LoginScreen

fun NavController.navigateToSLogin(
    navOptions: NavOptions?,
) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    composable<Login> {
        LoginScreen(
            modifier = Modifier,
            onClick = {}
        )
    }
}

@Serializable
data object Login : Route