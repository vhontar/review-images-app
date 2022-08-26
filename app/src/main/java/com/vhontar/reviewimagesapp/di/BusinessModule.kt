package com.vhontar.reviewimagesapp.di

import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSourceImpl
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSourceImpl
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BusinessModule {

    @Singleton
    @Provides
    fun provideHitsCacheDataSource(hitsDaoService: HitsDaoService): HitsCacheDataSource =
        HitsCacheDataSourceImpl(hitsDaoService)

    @Singleton
    @Provides
    fun provideHitsNetworkDataSource(hitsNetworkService: HitsNetworkService): HitsNetworkDataSource =
        HitsNetworkDataSourceImpl(hitsNetworkService)
}