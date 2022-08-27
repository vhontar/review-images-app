package com.vhontar.reviewimagesapp.di

import android.content.Context
import androidx.room.Room
import com.vhontar.reviewimagesapp.datasource.database.AppDatabase
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitDao
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoServiceImpl
import com.vhontar.reviewimagesapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHitDao(appDatabase: AppDatabase): HitDao = appDatabase.hitDao()
}