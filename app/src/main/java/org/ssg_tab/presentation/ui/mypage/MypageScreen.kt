package org.ssg_tab.presentation.ui.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.mypage.component.MenuListItem
import org.ssg_tab.presentation.ui.mypage.component.UserProfileCard


@Composable
fun MypageScreen(
    onNavigateToSetInteresting: () -> Unit,
    onNavigateToMyHistory: () -> Unit,
) {
    Scaffold(
        containerColor = SsgTabTheme.colors.White,

        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_bottomnav_mypage_off,
                middleText = "마이페이지",
                rightIcon = null,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserProfileCard(
                name = "김구름",
                level = 4,
                onEditClick = { /* TODO: 편집 버튼 클릭 로직 */ }
            )
            MenuListItem(
                text = "관심사 설정",
                onClick = onNavigateToSetInteresting
            )
            MenuListItem(
                text = "나의 히스토리",
                onClick = onNavigateToMyHistory
            )
            MenuListItem(
                text = "설정",
                onClick = { /* TODO: 설정 클릭 로직 */ }
            )
        }
    }
}

// 미리보기
@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    SsgTabTheme {
        MypageScreen(
            onNavigateToSetInteresting = {},
            onNavigateToMyHistory = {},
        )
    }
}