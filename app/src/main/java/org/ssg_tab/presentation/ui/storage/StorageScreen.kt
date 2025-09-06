package org.ssg_tab.presentation.ui.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.storage.component.StorageCategoryChip
import org.ssg_tab.presentation.ui.storage.component.StorageGridItem
import org.ssg_tab.presentation.ui.storage.component.StorageGridItemFolder
import org.ssg_tab.presentation.ui.storage.component.StorageTabButton
import org.ssg_tab.presentation.ui.storage.component.StorageTimeTabButton
import org.ssg_tab.presentation.ui.storage.component.TimeTabType

@Preview(showBackground = true)
@Composable
private fun PreviewStorageScreen() {
    SsgTabTheme {
        StorageScreen()
    }
}

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val categories = listOf("전체", "주식", "취업", "청약", "부동산", "암호화폐", "해외투자")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = SsgTabTheme.colors.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SsgTabTopBar(
            leftIcon = R.drawable.ic_core_save,
            middleText = "내 관심목록",
            rightIcon = R.drawable.ic_core_search,
        )

        Spacer(modifier = Modifier.height(4.dp))

        StorageTabButton(
            options = listOf("최신순", "분기별"),
            selectedOption = state.selectedSortOption,
            onOptionSelected = { viewModel.updateSelectedSortOption(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        when {
            state.isLoading -> {
                LoadingScreen()
            }

            else -> {
                when (state.selectedSortOption) {
                    "최신순" -> {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(categories) { category ->
                                StorageCategoryChip(
                                    content = category,
                                    isEnabled = state.selectedCategory == category,
                                    onClick = { viewModel.updateSelectedCategory(category) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        val contentsList = state.storageFeed?.contentsList ?: emptyList()

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(9.dp),
                        ) {
                            itemsIndexed(
                                items = contentsList,
                                key = { _, item -> item.id }
                            ) { _, item ->
                                StorageGridItem(
                                    title = item.title,
                                    imageUrl = item.imageUrl ?: "",
                                    isLiked = true
                                )
                            }
                        }
                    }

                    "분기별" -> {
                        StorageTimeTabButton(
                            selectedTab = TimeTabType.WEEK,
                            onTabSelected = { }
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        val timeData = generateTimeData()

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(9.dp),
                        ) {
                            itemsIndexed(
                                items = timeData,
                                key = { _, item -> item.time }
                            ) { _, item ->
                                StorageGridItemFolder(
                                    folderName = item.time,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


private fun generateTimeData(): List<GridWeek> {
    return List(6) { GridWeek(time = "2024년 ${it + 1}분기") }
}

data class GridWeek(
    val time: String,
)