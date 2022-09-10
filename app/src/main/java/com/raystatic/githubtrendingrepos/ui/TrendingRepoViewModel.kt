package com.raystatic.githubtrendingrepos.ui

import android.util.Log
import androidx.lifecycle.*
import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem
import com.raystatic.githubtrendingrepos.domain.repositories.TrendingReposRepository
import com.raystatic.githubtrendingrepos.utils.Constants
import com.raystatic.githubtrendingrepos.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TrendingRepoViewModel @Inject constructor(
    private val repository: TrendingReposRepository
): ViewModel() {

    val currentQuery = MutableLiveData(DEFAULT_QUERY)

    private val _trendingReposResponse = MutableLiveData<Resource<Boolean>>()
    val trendingReposResponse:LiveData<Resource<Boolean>> get() = _trendingReposResponse

    init {
        fetchTrendingRepos()
    }


    val trendingLiveData = currentQuery.switchMap { query->
       if (query.isNullOrEmpty()){
           repository.getTrendingRepos().asLiveData()
       }else{
           repository.getFilteredTrendingRepos(query).asLiveData()
       }
    }


    private fun fetchTrendingRepos() = viewModelScope.launch{
        repository.fetchTrendingRepos()
            .onEach {
                _trendingReposResponse.value = it
            }
            .catch {
                it.printStackTrace()
                val message = if (it is IOException){
                    Constants.NO_INTERNET_CONNECTION_MESSAGE
                }else{
                    Constants.SOMETHING_WENT_WRONG
                }
                _trendingReposResponse.value = Resource.error(message,null)
            }
            .launchIn(viewModelScope)
    }

    fun selectRepoItem(trendingRepoItem: TrendingRepoItem) = viewModelScope.launch {
        repository.selectTrendingItem(trendingRepoItem)
    }

    fun searchQuery(query:String){
        currentQuery.value = query
    }

    companion object{
        const val DEFAULT_QUERY = ""
    }

}