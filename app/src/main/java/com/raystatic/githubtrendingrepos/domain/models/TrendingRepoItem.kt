package com.raystatic.githubtrendingrepos.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raystatic.githubtrendingrepos.data.response_models.BuiltBy

@Entity(tableName = "trending_repo_item")
data class TrendingRepoItem(
    val builtBy: List<BuiltBy>? = null,
    val description: String? = null,
    val forks: Long? = null,
    val language: String? = null,
    val languageColor: String? = null,

    @PrimaryKey
    val rank: Int,

    val repositoryName: String? = null,
    val since: String? = null,
    val starsSince: Long? = null,
    val totalStars: Long? = null,
    val url: String? = null,
    val username: String? = null,
    val isSelected:Boolean?= false
)