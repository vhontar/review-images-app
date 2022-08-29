package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.paging.PagingSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitRemoteKeyModel
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity

interface HitsDaoService {
    suspend fun fetchHit(id: Int): HitModel?
    fun fetchAllHits(): PagingSource<Int, HitDatabaseEntity>
    suspend fun insertHit(model: HitModel)
    suspend fun insertAllHits(list: List<HitModel>)
    suspend fun deleteAllHits()
    suspend fun fetchLastRemoteKey(): HitRemoteKeyModel?
    suspend fun insertOrReplaceHitRemoteKey(remoteKey: HitRemoteKeyModel)
    suspend fun fetchHitRemoteKeyByQuery(query: String): HitRemoteKeyModel
    suspend fun deleteAllHitRemoteKeys()
    suspend fun <R> withTransaction(block: suspend () -> R): R
}