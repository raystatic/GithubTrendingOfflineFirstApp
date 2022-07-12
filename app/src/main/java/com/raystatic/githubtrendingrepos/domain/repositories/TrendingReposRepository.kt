package com.raystatic.githubtrendingrepos.domain.repositories

import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem
import com.raystatic.githubtrendingrepos.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TrendingReposRepository {

    suspend fun fetchTrendingRepos():Flow<Resource<Boolean>>

    fun getTrendingRepos():Flow<List<TrendingRepoItem>>

    fun getFilteredTrendingRepos(query:String):Flow<List<TrendingRepoItem>>

    suspend fun selectTrendingItem(trendingRepoItem: TrendingRepoItem)
}