package org.ssg_tab.presentation.ui.userinformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute

// 1단계: 관심사 선택 화면 주소
@Serializable
object SelectCategory

// 2단계: 연령대 및 지역 선택 화면 주소
@Serializable
object SelectAgeHome

// 3단계: 직업 선택 화면 주소
@Serializable
object SelectJob

fun NavController.navigateToSelectCategory(navOptions: NavOptions? = null) {
    this.navigate(SelectCategory, navOptions)
}