package com.vhontar.reviewimagesapp.business.usecase.loadlastquery

import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.utils.AppConstants
import javax.inject.Inject

class LoadLastQueryUseCaseImpl @Inject constructor(
    private val cacheDataSource: HitsCacheDataSource
): LoadLastQueryUseCase {
    override suspend fun invoke(data: Unit?): String {
        return cacheDataSource.fetchLastQuery()?.query ?: AppConstants.DEFAULT_REQUEST
    }
}