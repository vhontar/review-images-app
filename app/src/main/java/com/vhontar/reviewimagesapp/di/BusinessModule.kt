package com.vhontar.reviewimagesapp.di

import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSourceImpl
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSourceImpl
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCaseImpl
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCaseImpl
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

    @Singleton
    @Provides
    fun provideLoadHitUseCase(
        networkDataSource: HitsNetworkDataSource,
        cacheDataSource: HitsCacheDataSource
    ): LoadHitUseCase = LoadHitUseCaseImpl(networkDataSource, cacheDataSource)

    @Singleton
    @Provides
    fun provideLoadHitsUseCase(
        networkDataSource: HitsNetworkDataSource,
        cacheDataSource: HitsCacheDataSource
    ): LoadHitsUseCase = LoadHitsUseCaseImpl(networkDataSource, cacheDataSource)
}