package org.ssg_tab.presentation.ui.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.mypage.component.MenuListItem
import org.ssg_tab.presentation.ui.mypage.component.UserProfileCard
import org.ssg_tab.presentation.ui.mypage.model.MyPageViewModel

@Composable
fun MypageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onNavigateToSetInteresting: () -> Unit,
    onNavigateToMyHistory: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

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
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

//                uiState.error != null -> {
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = uiState.error,
//                            textAlign = TextAlign.Center,
//                            color = SsgTabTheme.colors.Error
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(onClick = { viewModel.retry() }) {
//                            Text("다시 시도")
//                        }
//                    }
//                }

                uiState.userInfo != null -> {
                    uiState.userInfo?.let { userInfo ->
                        UserProfileCard(
                            name = userInfo.name,
                            level = userInfo.level,
                            imageUrl = userInfo.profileImageUrl,
                            onEditClick = { /* TODO: 편집 버튼 클릭 로직 */ }
                        )
                    }
                }
            }

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