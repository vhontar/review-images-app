package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitLastQueryModel
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import javax.inject.Inject

class HitsCacheDataSourceImpl @Inject constructor(
    private val service: HitsDaoService
): HitsCacheDataSource {
    override suspend fun fetchHit(id: Int): HitModel? = service.fetchHit(id)
    override suspend fun fetchAllHits(): List<HitModel>? = service.fetchAllHits()
    override suspend fun insertHit(model: HitModel) = service.insertHit(model)
    override suspend fun insertAllHits(list: List<HitModel>) = service.insertAllHits(list)
    override suspend fun deleteAllHits() = service.deleteAllHits()
    override suspend fun fetchLastQuery(): HitLastQueryModel? = service.fetchLastQuery()
    override suspend fun insertLastQuery(remoteKey: HitLastQueryModel) = service.insertLastQuery(remoteKey)
    override suspend fun deleteLastQuery() = service.deleteLastQuery()
    override suspend fun <R> withTransaction(block: suspend () -> R): R = service.withTransaction { block() }
}