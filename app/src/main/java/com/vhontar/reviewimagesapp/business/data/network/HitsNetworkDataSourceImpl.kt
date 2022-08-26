package com.vhontar.reviewimagesapp.business.data.network

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkService
import javax.inject.Inject

class HitsNetworkDataSourceImpl @Inject constructor(
    private val service: HitsNetworkService
) : HitsNetworkDataSource {
    override suspend fun fetchHit(hitRequestModel: HitRequestModel): DataState<HitModel?> =
        service.fetchHit(hitRequestModel)

    override suspend fun fetchHits(
        hitsRequestModel: HitsRequestModel,
        page: Int,
        perPage: Int
    ): DataState<List<HitModel>> =
        service.fetchHits(hitsRequestModel, page, perPage)
}