package org.ssg_tab.data.repositoryimpl.quiz

import org.ssg_tab.data.dto.response.quiz.toEntity
import org.ssg_tab.data.remote.datasourceImpl.quiz.QuizDataSourceImpl
import org.ssg_tab.domain.model.entity.quiz.QuizEntity
import org.ssg_tab.domain.repository.quiz.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizDataSourceImpl: QuizDataSourceImpl,
) : QuizRepository {
    override suspend fun getQuizList(
        categoryId: Int,
        difficulty: String,
    ): Result<List<QuizEntity>> =
        runCatching {
            val response = quizDataSourceImpl.getQuizList(categoryId, difficulty)
            if (response.isSuccess && response.result != null) {
                response.result.quizList.map { it.toEntity() }
            } else {
                throw Exception(response.message ?: "퀴즈 데이터를 불러오는데 실패했습니다.")
            }
        }
}