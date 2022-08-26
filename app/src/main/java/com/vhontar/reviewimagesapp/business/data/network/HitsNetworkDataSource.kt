package com.vhontar.reviewimagesapp.business.data.network

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState

interface HitsNetworkDataSource {
    suspend fun fetchHit(hitRequestModel: HitRequestModel): DataState<HitModel?>
    suspend fun fetchHits(hitsRequestModel: HitsRequestModel, page: Int, perPage: Int): DataState<List<HitModel>>
}