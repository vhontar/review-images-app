package com.vhontar.reviewimagesapp.business.data.cache

import androidx.paging.PagingSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitRemoteKeyModel
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity
import javax.inject.Inject

class HitsCacheDataSourceImpl @Inject constructor(
    private val service: HitsDaoService
): HitsCacheDataSource {
    override suspend fun fetch(id: Int): HitModel? = service.fetchHit(id)
    override fun fetchAll(): PagingSource<Int, HitDatabaseEntity> = service.fetchAllHits()
    override suspend fun insert(model: HitModel) = service.insertHit(model)
    override suspend fun insertAll(list: List<HitModel>) = service.insertAllHits(list)
    override suspend fun deleteAll() = service.deleteAllHits()
    override suspend fun fetchLastRemoteKey(): HitRemoteKeyModel? = service.fetchLastRemoteKey()
    override suspend fun insertOrReplaceHitRemoteKey(remoteKey: HitRemoteKeyModel) = service.insertOrReplaceHitRemoteKey(remoteKey)
    override suspend fun fetchHitRemoteKeyByQuery(query: String) = service.fetchHitRemoteKeyByQuery(query)
    override suspend fun deleteAllHitRemoteKeys() = service.deleteAllHitRemoteKeys()
    override suspend fun <R> withTransaction(block: suspend () -> R): R = service.withTransaction { block() }
}