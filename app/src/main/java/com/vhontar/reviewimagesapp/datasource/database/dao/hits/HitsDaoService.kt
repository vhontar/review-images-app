package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

interface HitsDaoService {
    suspend fun fetch(id: Int): HitModel?
    suspend fun fetchAll(): List<HitModel>
    suspend fun insert(model: HitModel)
    suspend fun insertAll(list: List<HitModel>)
    suspend fun deleteAll()
}