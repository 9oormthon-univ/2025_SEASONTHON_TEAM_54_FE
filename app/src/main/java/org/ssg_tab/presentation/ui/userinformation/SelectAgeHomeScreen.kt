package org.ssg_tab.presentation.ui.userinformation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.unit.sp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.userinformation.component.SelectableChip
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingUiState
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingViewModel


@Composable
fun SelectAgeHomeScreen(
    viewModel: OnboardingViewModel,
    onNavigateBack: () -> Unit,
    onNextClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val ageGroups = listOf("20-24세", "25-29세", "30-34세", "35-41세", "기타")
    val regions = listOf(
        "서울", "경기", "인천", "강원", "대전", "세종",
        "충남", "충북", "부산", "울산", "대구", "경북",
        "경남", "전남", "전북", "광주", "제주"
    )

    val isRegionVisible = uiState.selectedAgeGroup != null
    val isButtonEnabled = uiState.selectedAgeGroup != null && uiState.selectedRegion != null

    SelectAgeHomeScreenContent(
        ageGroups = ageGroups,
        regions = regions,
        uiState = uiState,
        isRegionVisible = isRegionVisible,
        isButtonEnabled = isButtonEnabled,
        onAgeGroupSelected = viewModel::selectAgeGroup,
        onRegionSelected = viewModel::selectRegion,
        onNavigateBack = onNavigateBack,
        onNextClick = onNextClick
    )
}

@Composable
fun SelectAgeHomeScreenContent(
    ageGroups: List<String>,
    regions: List<String>,
    uiState: OnboardingUiState,
    isRegionVisible: Boolean,
    isButtonEnabled: Boolean,
    onAgeGroupSelected: (String) -> Unit,
    onRegionSelected: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNextClick: () -> Unit
) {
    Scaffold(
        containerColor = SsgTabTheme.colors.White,

        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                onLeftIconClick = onNavigateBack,
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
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_progress_bar_2),
                contentDescription = "progress_bar_step2",
                tint = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 메인 타이틀
            Column {
                Text(text = "딱 맞는 추천을 위해", style = SsgTabTheme.typography.header)
                Text(text = "연령대와 사는 지역을 선택해주세요", style = SsgTabTheme.typography.header)
            }
            Spacer(modifier = Modifier.height(40.dp))

            // 연령대 선택
            Text(text = "연령대", style = SsgTabTheme.typography.Regular_Sb.copy(
                fontSize = 16.sp,
                color = SsgTabTheme.colors.MidGray,
            ))
            Spacer(modifier = Modifier.height(6.dp))
            SelectableChipGrid(
                items = ageGroups,
                selectedItem = uiState.selectedAgeGroup,
                onItemSelected = onAgeGroupSelected
            )

            // 사는 지역 선택 (연령대 선택 후 보여짐)
            AnimatedVisibility(visible = isRegionVisible) {
                Column {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "사는 지역", style = SsgTabTheme.typography.Regular_Sb.copy(
                        fontSize = 16.sp,
                        color = SsgTabTheme.colors.MidGray,
                    ))
                    Spacer(modifier = Modifier.height(6.dp))
                    SelectableChipGrid(
                        items = regions,
                        selectedItem = uiState.selectedRegion,
                        onItemSelected = onRegionSelected
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

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


@Composable
fun SelectableChipGrid(
    items: List<String>,
    selectedItem: String?,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 80.dp),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items) { item ->
            SelectableChip(
                text = item,
                isSelected = item == selectedItem,
                onClick = { onItemSelected(item) }
            )
        }
    }
}

@Preview(showBackground = true, name = "지역 선택 표시 상태")
@Composable
private fun SelectAgeHomeScreen_RegionVisible_Preview() {
    SsgTabTheme {
        SelectAgeHomeScreenContent(
            ageGroups = listOf("20-24세", "25-29세", "30-34세", "기타"),
            regions = listOf(
                "서울", "경기", "인천", "강원", "대전", "세종",
                "충남", "충북", "부산", "울산", "대구", "경북",
                "경남", "전남", "전북", "광주", "제주"
            ),
            uiState = OnboardingUiState(selectedAgeGroup = "25-29세"), // UiState 객체로 상태 전달
            isRegionVisible = true,
            isButtonEnabled = false,
            onAgeGroupSelected = {},
            onRegionSelected = {},
            onNavigateBack = {},
            onNextClick = {}
        )
    }
}