package com.raystatic.githubtrendingrepos.data.repositories

import androidx.room.withTransaction
import com.raystatic.githubtrendingrepos.data.api.ApiService
import com.raystatic.githubtrendingrepos.data.response_models.toTrendingRepoItem
import com.raystatic.githubtrendingrepos.data.room.TrendingReposDB
import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem
import com.raystatic.githubtrendingrepos.domain.repositories.TrendingReposRepository
import com.raystatic.githubtrendingrepos.utils.Constants
import com.raystatic.githubtrendingrepos.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingReposRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: TrendingReposDB
): TrendingReposRepository {

    private val trendingRepoDao = db.getTrendingRepoDao()

    override suspend fun fetchTrendingRepos() = flow{
        emit(Resource.loading())
        val response = apiService.getTrendingRepositories()
        if (response.isSuccessful){
            val trendingRepoItems = mutableListOf<TrendingRepoItem>()
            response.body()?.forEach { responseRepo ->
                trendingRepoItems.add(responseRepo.toTrendingRepoItem())
            }
            db.withTransaction {
                trendingRepoDao.clearTrendingRepos()
                trendingRepoDao.insertTrendingRepos(trendingRepoItems)
            }
            emit(Resource.success(true))
        }else{
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG, null))
        }
    }

    override fun getTrendingRepos(): Flow<List<TrendingRepoItem>> {
        return trendingRepoDao.getTrendingRepos()
    }

    override suspend fun selectTrendingItem(trendingRepoItem: TrendingRepoItem) {
        val selected= trendingRepoItem.isSelected == false
        trendingRepoDao.updateTrendingRepoItem(selected,trendingRepoItem.rank)
    }

    override fun getFilteredTrendingRepos(query: String): Flow<List<TrendingRepoItem>> {
        return trendingRepoDao.getFilteredTrendingRepos("%$query%")
    }
}