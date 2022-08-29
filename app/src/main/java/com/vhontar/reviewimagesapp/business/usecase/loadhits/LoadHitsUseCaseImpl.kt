package com.vhontar.reviewimagesapp.business.usecase.loadhits

import androidx.paging.*
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModel
import com.vhontar.reviewimagesapp.datasource.mediator.HitsRemoteMediator
import com.vhontar.reviewimagesapp.business.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/*
 * Trade-off
 * HitRemoteMediator knows about HitDatabaseEntity (which must be hidden from outer code)
 * PagingSource is not possible to convert to domain models before creation of Pager
 * */
class LoadHitsUseCaseImpl @Inject constructor(
    private val networkDataSource: HitsNetworkDataSource,
    private val cacheDataSource: HitsCacheDataSource
): LoadHitsUseCase {
    @OptIn(ExperimentalPagingApi::class)
    override fun invoke(loadRequestModel: (() -> HitsRequestModel)?): Flow<PagingData<HitModel>> {
        // fail-fast, request model is required to fetch data
        if (loadRequestModel == null)
            throw IllegalArgumentException("Hits request model is null.")

        val pageConfig = PagingConfig(pageSize = AppConstants.DEFAULT_IMAGES_PER_PAGE)

        return Pager(
            config = pageConfig,
            remoteMediator = HitsRemoteMediator(networkDataSource, cacheDataSource) { loadRequestModel.invoke() }
        ) {
            cacheDataSource.fetchAll()
        }.flow.map { paging -> paging.map { it.toDomainModel() } }
    }
}