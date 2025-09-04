package org.ssg_tab.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import org.ssg_tab.R
import org.ssg_tab.core.navigation.MainTabRoute
import org.ssg_tab.core.navigation.Route
import org.ssg_tab.presentation.ui.guide.navigation.Guide
import org.ssg_tab.presentation.ui.home.navigation.Home
import org.ssg_tab.presentation.ui.mypage.navigation.Mypage
import org.ssg_tab.presentation.ui.storage.navigation.Storage

enum class MainTab(
    @DrawableRes val selectedIconResource: Int,
    @DrawableRes val unselectedIconResource: Int,
    val label: String,
    val route: MainTabRoute
) {
    HOME(
        selectedIconResource = R.drawable.ic_bottomnav_home_on,
        unselectedIconResource = R.drawable.ic_bottomnav_home_off,
        label = "홈",
        route = Home
    ),
    STORAGE(
        selectedIconResource = R.drawable.ic_bottomnav_save_onf,
        unselectedIconResource = R.drawable.ic_bottomnav_save_off,
        label = "보관함",
        route = Storage
    ),
    GUIDE(
        selectedIconResource = R.drawable.ic_bottomnav_study_on,
        unselectedIconResource = R.drawable.ic_bottomnav_study_off,
        label = "가이드",
        route = Guide
    ),
    MYPAGE(
        selectedIconResource = R.drawable.ic_bottomnav_mypage_on,
        unselectedIconResource = R.drawable.ic_bottomnav_mypage_off,
        label = "마이",
        route = Mypage
    )
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}