package com.raystatic.githubtrendingrepos.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem

@Database(
    version = 1,
    entities = [TrendingRepoItem::class]
)
@TypeConverters(
    BuiltByConverters::class
)
abstract class TrendingReposDB: RoomDatabase() {
    abstract fun getTrendingRepoDao():TrendingReposDao
}