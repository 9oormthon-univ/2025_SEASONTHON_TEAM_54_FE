package org.ssg_tab.domain.model.entity.quiz

data class QuizEntity(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
)