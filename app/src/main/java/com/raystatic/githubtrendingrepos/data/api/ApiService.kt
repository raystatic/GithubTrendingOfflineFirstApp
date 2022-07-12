package com.raystatic.githubtrendingrepos.data.api

import com.raystatic.githubtrendingrepos.data.response_models.TrendingReposResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BASEURL = "https://gh-trending-api.herokuapp.com/"
    }

    @GET("repositories")
    suspend fun getTrendingRepositories():Response<List<TrendingReposResponseItem>>

}