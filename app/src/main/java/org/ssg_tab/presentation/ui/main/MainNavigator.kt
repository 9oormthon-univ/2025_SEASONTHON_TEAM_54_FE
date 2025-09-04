package org.ssg_tab.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.ssg_tab.presentation.ui.guide.navigation.navigateToGuide
import org.ssg_tab.presentation.ui.home.navigation.Home
import org.ssg_tab.presentation.ui.home.navigation.navigateToHome
import org.ssg_tab.presentation.ui.login.navigation.Login
import org.ssg_tab.presentation.ui.mypage.navigation.navigateToMypage
import org.ssg_tab.presentation.ui.storage.navigation.navigateToStorage

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Home

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.STORAGE -> navController.navigateToStorage(navOptions)
            MainTab.GUIDE -> navController.navigateToGuide(navOptions)
            MainTab.MYPAGE -> navController.navigateToMypage(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    inline fun isCurrentDestination(destination: NavDestination): Boolean {
        return navController.currentDestination == destination
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}