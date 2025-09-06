package org.ssg_tab.data.repositoryimpl.quiz

import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto
import org.ssg_tab.data.remote.datasourceImpl.quiz.QuizCompleteDataSourceImpl
import org.ssg_tab.domain.repository.quiz.QuizCompleteRepository
import javax.inject.Inject

class QuizCompleteRepositoryImpl @Inject constructor(
    private val quizCompleteDataSourceImpl: QuizCompleteDataSourceImpl
): QuizCompleteRepository {
    override suspend fun completeQuiz(quizCompleteRequestDto:QuizCompleteRequestDto): Result<Unit> =
        runCatching {
            quizCompleteDataSourceImpl.completeQuiz(quizCompleteRequestDto)
        }
}