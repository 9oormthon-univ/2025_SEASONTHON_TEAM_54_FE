package org.ssg_tab.presentation.ui.storage.state

import androidx.compose.runtime.Immutable
import org.ssg_tab.data.dto.response.storage.StorageResponseDto

@Immutable
data class StorageContract(
    val isLoading: Boolean = false,
    val error: String? = null,
    val storageFeed: StorageResponseDto? = null,
    val selectedCategory: String = "전체",
    val selectedSortOption: String = "최신순"
)