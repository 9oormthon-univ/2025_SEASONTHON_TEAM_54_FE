package org.ssg_tab.presentation.ui.guide.state

import androidx.compose.runtime.Immutable
import org.ssg_tab.data.dto.response.study.StudyResponseDto

@Immutable
data class GuideContract(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studyData: StudyResponseDto? = null
)