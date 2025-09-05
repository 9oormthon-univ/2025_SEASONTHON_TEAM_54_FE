package org.ssg_tab.data.repositoryimpl.quiz

import org.ssg_tab.data.dto.response.quiz.toEntity
import org.ssg_tab.data.remote.datasourceImpl.QuizDataSourceImpl
import org.ssg_tab.domain.model.entity.QuizEntity
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
            quizDataSourceImpl.getQuizList(
                categoryId,
                difficulty
            ).result.quizList.map { it.toEntity() }
        }
}