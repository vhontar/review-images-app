package com.vhontar.reviewimagesapp.business.usecase.loadhit

import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState
import javax.inject.Inject

class LoadHitUserCaseImpl @Inject constructor(
    private val networkDataSource: HitsNetworkDataSource
): LoadHitUseCase {
    override suspend fun invoke(requestModel: HitRequestModel?): DataState<HitModel?> {
        // fail-fast, request model is required to fetch data
        if (requestModel == null)
            throw IllegalArgumentException("Hit request model is null.")

        return networkDataSource.fetchHit(requestModel)
    }
}