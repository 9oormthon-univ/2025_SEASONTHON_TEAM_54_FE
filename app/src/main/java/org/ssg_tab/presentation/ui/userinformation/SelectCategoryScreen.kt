package org.ssg_tab.presentation.ui.userinformation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.userinformation.component.InterestChip
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingViewModel


@Composable
fun SelectCategoryScreen(
    viewModel: OnboardingViewModel,
    onNextClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val interests = listOf(
        "주식", "취업", "부동산", "청약", "암호화폐", "해외투자", "금융", "펀드", "경제상식"
    )

    val isButtonEnabled = uiState.selectedInterests.size in 3..5

    SelectCategoryScreenContent(
        interests = interests,
        selectedInterests = uiState.selectedInterests,
        isButtonEnabled = isButtonEnabled,
        onInterestClick = viewModel::selectInterest,
        onNextClick = onNextClick
    )
}

@Composable
fun SelectCategoryScreenContent(
    interests: List<String>,
    selectedInterests: Set<String>,
    isButtonEnabled: Boolean,
    onInterestClick: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Scaffold(
        containerColor = SsgTabTheme.colors.White,
        topBar = {
            SsgTabTopBar(
                leftIcon = null,
                middleText = "",
                rightIcon = null,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_progress_bar),
                contentDescription = "progress_bar_step1",
                tint = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 메인 타이틀
            Column {
                Text(text = "어디부터 시작할지 막막하다면,", style = SsgTabTheme.typography.header)
                Text(text = "일단 끌리는 걸 골라주세요!", style = SsgTabTheme.typography.header)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "3-5개를 선택해주세요",
                style = SsgTabTheme.typography.Small_R.copy(
                    color = SsgTabTheme.colors.SoftGray
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 카테고리 선택
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(interests) { interest ->
                    InterestChip(
                        text = interest,
                        isSelected = selectedInterests.contains(interest),
                        onClick = { onInterestClick(interest) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 다음 버튼
            SsgTabButton(
                name = "다음",
                isEnabled = isButtonEnabled,
                onClick = onNextClick
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Preview(showBackground = true, name = "Initial State")
@Composable
private fun SelectCategoryScreen_Initial_Preview() {
    SsgTabTheme {
        SelectCategoryScreen(
            viewModel = viewModel(),
            onNextClick = {},
        )
    }
}

@Preview(showBackground = true, name = "Selected State")
@Composable
private fun SelectCategoryScreen_Selected_Preview() {
    SsgTabTheme {
        SelectCategoryScreenContent(
            interests = listOf(
                "주식", "취업", "부동산", "청약", "암호화폐", "해외투자", "금융", "펀드", "경제상식"
            ),
            selectedInterests = setOf("주식", "취업", "청약"),
            isButtonEnabled = true,
            onInterestClick = {},
            onNextClick = {}
        )
    }
}