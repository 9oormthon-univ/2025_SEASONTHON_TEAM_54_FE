package org.ssg_tab.presentation.ui.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        StorageScreen(
            name = List(15) { GridItem(name = "항목 $it") },
            time = List(6) { GridWeek(time = "2024년 ${it + 1}분기") }
        )
    }
}

@Composable
fun StorageScreen(
    name: List<GridItem>,
    time: List<GridWeek>,
    modifier: Modifier = Modifier,
) {
    val categories = listOf("전체", "주식", "취업", "청약", "부동산", "암호화폐", "해외투자")
    var selectedCategory by remember { mutableStateOf("전체") }
    var selectedSortOption by remember { mutableStateOf("최신순") }

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
            selectedOption = selectedSortOption,
            onOptionSelected = { selectedSortOption = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (selectedSortOption) {
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
                            isEnabled = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    itemsIndexed(
                        items = name,
                        key = { _, item -> item.name }
                    ) { _, item ->
                        StorageGridItem(
                            title = "${item.name}",
                            imageUrl = "",
                            isLiked = true
                        )
                    }
                }
            }

            "분기별" -> {
                StorageTimeTabButton(
                    selectedTab = TimeTabType.WEEK,
                    onTabSelected =
                        { }
                )
                Spacer(modifier = Modifier.height(14.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    itemsIndexed(
                        items = time,
                        key = { _, item -> item.time }
                    ) { _, item ->
                        StorageGridItemFolder(
                            folderName = "${item.time}",
                        )
                    }
                }
            }
        }
    }
}

data class GridItem(
    val name: String,
)

data class GridWeek(
    val time: String,
)