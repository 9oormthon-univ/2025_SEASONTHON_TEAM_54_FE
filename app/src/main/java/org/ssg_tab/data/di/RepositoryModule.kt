package org.ssg_tab.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.ssg_tab.data.repositoryimpl.DummyRepositoryImpl
import org.ssg_tab.data.repositoryimpl.home.HomeFeedRepositoryImpl
import org.ssg_tab.data.repositoryimpl.home.HomeLikeRepositoryImpl
import org.ssg_tab.data.repositoryimpl.login.AuthRepositoryImpl
import org.ssg_tab.data.repositoryimpl.onboarding.OnboardingRepositoryImpl
import org.ssg_tab.data.repositoryimpl.quiz.QuizCompleteRepositoryImpl
import org.ssg_tab.data.repositoryimpl.quiz.QuizRepositoryImpl
import org.ssg_tab.data.repositoryimpl.storage.StorageRepositoryImpl
import org.ssg_tab.domain.repository.DummyRepository
import org.ssg_tab.domain.repository.home.HomeFeedRepository
import org.ssg_tab.domain.repository.home.HomeLikeRepository
import org.ssg_tab.domain.repository.login.AuthRepository
import org.ssg_tab.domain.repository.onboarding.OnboardingRepository
import org.ssg_tab.domain.repository.quiz.QuizCompleteRepository
import org.ssg_tab.domain.repository.quiz.QuizRepository
import org.ssg_tab.domain.repository.storage.StorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl,
    ): DummyRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl,
    ): QuizRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeFeedRepositoryImpl: HomeFeedRepositoryImpl,
    ): HomeFeedRepository

    @Binds
    abstract fun bindQuizCompleteRepository(
        quizCompleteRepositoryImpl: QuizCompleteRepositoryImpl
    ): QuizCompleteRepository

    @Binds
    abstract fun bindHomeLikeRepository(
        homeLikeRepositoryImpl: HomeLikeRepositoryImpl
    ): HomeLikeRepository

    @Binds
    abstract fun bindStorageRepository(
        storageRepositoryImpl: StorageRepositoryImpl
    ): StorageRepository
}