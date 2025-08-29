package org.ssg_tab.presentation.ui.storage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.presentation.ui.storage.StorageScreen

fun NavController.navigateToStorage(
    navOptions: NavOptions? = null
) {
    navigate(Storage, navOptions)
}

fun NavGraphBuilder.storageNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    composable<Storage> {
        StorageScreen()
    }
}

@Serializable
data object Storage : MainTabRoute