package com.raystatic.githubtrendingrepos.data.room

import androidx.room.*
import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingRepos(trendingRepos:List<TrendingRepoItem>)

    @Query("SELECT * FROM trending_repo_item")
    fun getTrendingRepos():Flow<List<TrendingRepoItem>>

    @Query("SELECT * FROM trending_repo_item WHERE repositoryName like :query")
    fun getFilteredTrendingRepos(query:String):Flow<List<TrendingRepoItem>>

    @Query("UPDATE trending_repo_item SET isSelected =:isSelected WHERE rank=:rank")
    suspend fun updateTrendingRepoItem(isSelected:Boolean,rank:Int)

    @Query("DELETE FROM trending_repo_item")
    suspend fun clearTrendingRepos()

}