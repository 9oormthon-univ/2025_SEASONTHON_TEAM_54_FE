package org.ssg_tab.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.ssg_tab.data.remote.datasource.AuthRemoteDataSource
import org.ssg_tab.data.remote.datasourceImpl.AuthRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}