package org.ssg_tab.data.dto.request.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizCompleteRequestDto (
    @Serializable
    val quizId: Int,
)