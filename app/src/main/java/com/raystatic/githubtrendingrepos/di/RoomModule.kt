package com.raystatic.githubtrendingrepos.di

import android.content.Context
import androidx.room.Room
import com.raystatic.githubtrendingrepos.data.room.TrendingReposDB
import com.raystatic.githubtrendingrepos.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TrendingReposDB::class.java,
        Constants.DB_NAME
    ).build()

}