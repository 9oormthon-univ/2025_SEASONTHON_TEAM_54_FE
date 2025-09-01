package org.ssg_tab.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.ssg_tab.data.repositoryimpl.DummyRepositoryImpl
import org.ssg_tab.data.repositoryimpl.login.AuthRepositoryImpl
import org.ssg_tab.domain.repository.DummyRepository
import org.ssg_tab.domain.repository.login.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl
    ): DummyRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

}