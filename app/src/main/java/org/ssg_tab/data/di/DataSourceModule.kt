package org.ssg_tab.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.ssg_tab.data.remote.datasource.AuthRemoteDataSource
import org.ssg_tab.data.remote.datasource.home.HomeFeedDataSource
import org.ssg_tab.data.remote.datasource.home.HomeLikeDataSource
import org.ssg_tab.data.remote.datasource.quiz.QuizCompleteDataSource
import org.ssg_tab.data.remote.datasource.quiz.QuizDataSource
import org.ssg_tab.data.remote.datasource.storage.StorageDataSource
import org.ssg_tab.data.remote.datasourceImpl.AuthRemoteDataSourceImpl
import org.ssg_tab.data.remote.datasourceImpl.home.HomeFeedDataSourceImpl
import org.ssg_tab.data.remote.datasourceImpl.quiz.QuizDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindQuizRemoteDataSource(
        quizRemoteDataSourceImpl: QuizDataSourceImpl
    ): QuizDataSource

    @Binds
    abstract fun bindHomeRemoteDataSource(
        homeRemoteDataSourceImpl: HomeFeedDataSourceImpl
    ): HomeFeedDataSource

    @Binds
    abstract fun bindQuizCompleteRemoteDataSource(
        quizCompleteDataSource: QuizCompleteDataSource
    ): QuizCompleteDataSource


    @Binds
    abstract fun bindHomeLikeRemoteDataSource(
        homeLikeDataSource: HomeLikeDataSource
    ): HomeLikeDataSource

    @Binds
    abstract fun bindStorageRemoteDataSource(
        storageDataSource: StorageDataSource
    ): StorageDataSource
}
