package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

interface HitsCacheDataSource {
    suspend fun fetch(id: Int): HitModel?
    suspend fun fetchAll(): List<HitModel>
    suspend fun insert(model: HitModel)
    suspend fun insertAll(list: List<HitModel>)
    suspend fun deleteAll()
}