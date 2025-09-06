package org.ssg_tab.domain.repository.quiz

import org.ssg_tab.domain.model.entity.quiz.QuizEntity

interface QuizRepository {
    suspend fun getQuizList(categoryId: Int, difficulty: String): Result<List<QuizEntity>>
}