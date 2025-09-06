package org.ssg_tab.data.dto.response.study

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.presentation.ui.storage.component.ProgressItem

@Serializable
data class StudyResponseDto(
    @SerialName("nickname")  // name → nickname 변경
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("level")
    val level: Long,
    @SerialName("categoryLearningInfo")
    val categoryLearningInfo: List<LearningList>
)

@Serializable
data class LearningList(
    @SerialName("name")
    val name: String,
    @SerialName("progress")
    val progress: Long
)

fun StudyResponseDto.toEntity(): StudyResponseDto = this

fun StudyResponseDto.toProgressItems(): List<ProgressItem> {
    return categoryLearningInfo.map { learning ->
        ProgressItem(
            title = learning.name,
            progress = learning.progress / 100f
        )
    }
}