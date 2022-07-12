package com.raystatic.githubtrendingrepos.data.response_models

import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem

data class TrendingReposResponseItem(
    val builtBy: List<BuiltBy>? = null,
    val description: String? = null,
    val forks: Long? = null,
    val language: String? = null,
    val languageColor: String? = null,
    val rank: Int,
    val repositoryName: String? = null,
    val since: String? = null,
    val starsSince: Long? = null,
    val totalStars: Long? = null,
    val url: String? = null,
    val username: String? = null
)

fun TrendingReposResponseItem.toTrendingRepoItem():TrendingRepoItem{
    return TrendingRepoItem(
        builtBy = this.builtBy,
        description = this.description,
        forks = this.forks,
        language = this.language,
        rank = this.rank,
        since = this.since,
        starsSince = this.starsSince,
        totalStars = this.totalStars,
        url = this.url,
        username = this.username,
        repositoryName = this.repositoryName,
        languageColor = this.languageColor
    )
}