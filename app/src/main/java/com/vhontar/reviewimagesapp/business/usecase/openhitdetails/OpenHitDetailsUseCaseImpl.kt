package com.vhontar.reviewimagesapp.business.usecase.openhitdetails

import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel

class OpenHitDetailsUseCaseImpl(
    private val cacheDataSource: HitsCacheDataSource
): OpenHitDetailsUseCase {
    override suspend fun invoke(data: HitModel?) {
        if (data == null)
            return

        cacheDataSource.insert(data)
    }
}