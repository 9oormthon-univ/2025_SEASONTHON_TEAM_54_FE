package org.ssg_tab.data.repositoryimpl.quiz

import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto
import org.ssg_tab.data.remote.datasourceImpl.quiz.QuizCompleteDataSourceImpl
import org.ssg_tab.domain.repository.quiz.QuizCompleteRepository
import javax.inject.Inject

class QuizCompleteRepositoryImpl @Inject constructor(
    private val quizCompleteDataSourceImpl: QuizCompleteDataSourceImpl
) : QuizCompleteRepository {
    override suspend fun completeQuiz(quizCompleteRequestDto: QuizCompleteRequestDto): Result<Unit> =
        runCatching {
            val response = quizCompleteDataSourceImpl.completeQuiz(quizCompleteRequestDto)
            if (response.isSuccess) {
                Unit
            } else {
                throw Exception(response.message ?: "퀴즈 완료 처리에 실패했습니다.")
            }
        }
}