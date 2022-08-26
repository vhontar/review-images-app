package com.vhontar.reviewimagesapp.datasource.network

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState
import com.vhontar.reviewimagesapp.datasource.network.common.NetworkHandledResult
import com.vhontar.reviewimagesapp.datasource.network.common.handle
import com.vhontar.reviewimagesapp.datasource.network.common.makeSafeCall
import com.vhontar.reviewimagesapp.datasource.network.entities.toModel
import com.vhontar.reviewimagesapp.datasource.network.entities.toModels
import javax.inject.Inject

class HitsNetworkServiceImpl @Inject constructor(
    private val networkService: NetworkService
): HitsNetworkService {

    override suspend fun fetchHit(hitRequestModel: HitRequestModel): DataState<HitModel?> {
        val result = makeSafeCall {
            networkService.fetchHit(id = hitRequestModel.id).handle()
        }

        return when(result) {
            is NetworkHandledResult.Success -> DataState.data(result.data?.hit?.toModel())
            is NetworkHandledResult.ResponseError -> DataState.error(result.error, result.exception)
        }
    }

    override suspend fun fetchHits(hitsRequestModel: HitsRequestModel, page: Int, perPage: Int): DataState<List<HitModel>> {
        val result = makeSafeCall {
            networkService.fetchHits(
                query = hitsRequestModel.query,
                page = page,
                perPage = perPage
            ).handle()
        }

        return when(result) {
            is NetworkHandledResult.Success -> DataState.data(result.data?.hits?.toModels() ?: listOf())
            is NetworkHandledResult.ResponseError -> DataState.error(result.error, result.exception)
        }
    }
}