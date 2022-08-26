package com.vhontar.reviewimagesapp.datasource.network

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState

interface HitsNetworkService {
    suspend fun fetchHit(hitRequestModel: HitRequestModel): DataState<HitModel?>
    suspend fun fetchHits(hitsRequestModel: HitsRequestModel, page: Int, perPage: Int): DataState<List<HitModel>>
}