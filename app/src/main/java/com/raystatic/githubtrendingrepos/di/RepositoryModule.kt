package com.raystatic.githubtrendingrepos.di

import com.raystatic.githubtrendingrepos.data.repositories.TrendingReposRepositoryImpl
import com.raystatic.githubtrendingrepos.domain.repositories.TrendingReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrendingRepository(
        trendingReposRepositoryImpl: TrendingReposRepositoryImpl
    ):TrendingReposRepository = trendingReposRepositoryImpl


}