package com.vhontar.reviewimagesapp.di

import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSourceImpl
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSourceImpl
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCaseImpl
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCaseImpl
import com.vhontar.reviewimagesapp.business.usecase.loadlastquery.LoadLastQueryUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadlastquery.LoadLastQueryUseCaseImpl
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoServiceImpl
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkService
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    @Binds
    abstract fun bindHitsCacheDataSource(hitsCacheDataSource: HitsCacheDataSourceImpl): HitsCacheDataSource

    @Binds
    abstract fun bindHitsNetworkDataSource(hitsNetworkDataSource: HitsNetworkDataSourceImpl): HitsNetworkDataSource

    @Binds
    abstract fun bindLoadHitUseCase(loadHitsUseCase: LoadHitUseCaseImpl): LoadHitUseCase

    @Binds
    abstract fun bindLoadHitsUseCase(loadHitsUseCase: LoadHitsUseCaseImpl): LoadHitsUseCase

    @Binds
    abstract fun bindLoadLastQueryUseCase(loadLastQueryUseCase: LoadLastQueryUseCaseImpl): LoadLastQueryUseCase

    @Binds
    abstract fun bindHitDaoService(hiltDaoService: HitsDaoServiceImpl): HitsDaoService

    @Binds
    abstract fun bindHitNetworkService(hitsNetworkService: HitsNetworkServiceImpl): HitsNetworkService

}