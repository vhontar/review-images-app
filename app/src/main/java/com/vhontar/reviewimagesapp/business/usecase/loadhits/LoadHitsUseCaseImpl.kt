package com.vhontar.reviewimagesapp.business.usecase.loadhits

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.utils.AppConstants
import com.vhontar.reviewimagesapp.view.home.paging.HitsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Trade-off
// Ideally, business logic shouldn't know anything about jetpack
// It's impossible to test use-case
// it might be better to use Paging3 in ViewModel, without use-case
class LoadHitsUseCaseImpl @Inject constructor(
    private val networkDataSource: HitsNetworkDataSource,
    private val cacheDataSource: HitsCacheDataSource
): LoadHitsUseCase {
    override fun invoke(loadRequestModel: (() -> HitsRequestModel)?): Flow<PagingData<HitModel>> {
        // fail-fast, request model is required to fetch data
        if (loadRequestModel == null)
            throw IllegalArgumentException("Hits request model is null.")

        val pageConfig = PagingConfig(pageSize = AppConstants.DEFAULT_IMAGES_PER_PAGE)

        return Pager(pageConfig) {
            HitsPagingSource(
                networkDataSource = networkDataSource,
                cacheDataSource = cacheDataSource,
                loadRequestModel = loadRequestModel
            )
        }.flow
    }
}