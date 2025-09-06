package org.ssg_tab.presentation.ui.home.state

import androidx.compose.runtime.Immutable
import org.ssg_tab.domain.model.entity.home.HomeFeedEntity

@Immutable
data class HomeContract(
    val homeFeed: HomeFeedEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)