package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitLastQueryModel

interface HitsDaoService {
    suspend fun fetchHit(id: Int): HitModel?
    suspend fun fetchAllHits(): List<HitModel>?
    suspend fun insertHit(model: HitModel)
    suspend fun insertAllHits(list: List<HitModel>)
    suspend fun deleteAllHits()
    suspend fun fetchLastQuery(): HitLastQueryModel?
    suspend fun insertLastQuery(remoteKey: HitLastQueryModel)
    suspend fun deleteLastQuery()
    suspend fun <R> withTransaction(block: suspend () -> R): R
}