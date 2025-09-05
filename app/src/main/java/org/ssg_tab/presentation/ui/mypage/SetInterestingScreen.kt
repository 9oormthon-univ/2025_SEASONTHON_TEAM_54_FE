package org.ssg_tab.presentation.ui.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.mypage.component.InterestChip


@Composable
fun SetInterestingScreen() {

    // 카테고리 목록
    val categories = listOf(
        "주식", "취업", "부동산", "청약", "암호화폐", "해외투자", "금융", "펀드", "경제상식"
    )

    var selectedCategories by remember { mutableStateOf<Set<String>>(setOf("주식", "청약")) }
    // var selectedCategories by remember { mutableStateOf<Set<String>>(emptySet()) }

    // (3~5개 선택) > 버튼 활성화
    val isButtonEnabled = selectedCategories.size in 3..5

    InterestSettingContent(
        categories = categories,
        selectedCategories = selectedCategories,
        isButtonEnabled = isButtonEnabled,
        onCategoryClick = { category ->
            val newSelected = selectedCategories.toMutableSet()
            if (newSelected.contains(category)) {
                newSelected.remove(category)
            } else {
                newSelected.add(category)
            }
            selectedCategories = newSelected
        },
        onSaveClick = {
            // TODO: '저장' 버튼 클릭 시 로직 구현
        }
    )
}

@Composable
fun InterestSettingContent(
    categories: List<String>,
    selectedCategories: Set<String>,
    isButtonEnabled: Boolean,
    onCategoryClick: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                middleText = "",
                rightIcon = null
            )
        },
        containerColor = SsgTabTheme.colors.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // 요즘 관심가는 키워드가 생겼나요?
            Column {
                Text(
                    text = "요즘 관심가는 키워드가 생겼나요?",
                    style = SsgTabTheme.typography.header
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "3-5개를 선택해주세요",
                    style = SsgTabTheme.typography.Small_Sb.copy(
                        color = SsgTabTheme.colors.SoftGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 카테고리 선택
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    InterestChip(
                        text = category,
                        isSelected = selectedCategories.contains(category),
                        onClick = { onCategoryClick(category) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 저장 버튼
            SsgTabButton(
                name = "저장",
                isEnabled = isButtonEnabled,
                onClick = onSaveClick
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InterestSettingScreenPreview() {
    SsgTabTheme {
        SetInterestingScreen()
    }
}