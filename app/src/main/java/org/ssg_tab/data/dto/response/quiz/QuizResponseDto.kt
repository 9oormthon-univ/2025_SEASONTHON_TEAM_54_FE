package org.ssg_tab.data.dto.response.quiz

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.domain.model.entity.quiz.QuizEntity

@Serializable
data class QuizListResponseDto(
    @SerialName("quizList")
    val quizList: List<QuizResponseDto>,
)

@Serializable
data class QuizResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("question")
    val question: String,
    @SerialName("options")
    val options: List<String>,
    @SerialName("correctIndex")
    val correctIndex: Int,
)

fun QuizResponseDto.toEntity(): QuizEntity {
    return QuizEntity(
        id = this.id,
        question = this.question,
        options = this.options,
        correctAnswerIndex = this.correctIndex
    )
}