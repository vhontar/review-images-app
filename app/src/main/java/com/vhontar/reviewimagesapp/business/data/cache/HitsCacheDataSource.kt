package com.vhontar.reviewimagesapp.business.data.cache

import androidx.paging.PagingSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitRemoteKeyModel
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity

interface HitsCacheDataSource {
    suspend fun fetch(id: Int): HitModel?
    fun fetchAll(): PagingSource<Int, HitDatabaseEntity>
    suspend fun insert(model: HitModel)
    suspend fun insertAll(list: List<HitModel>)
    suspend fun deleteAll()
    suspend fun fetchLastRemoteKey(): HitRemoteKeyModel?
    suspend fun insertOrReplaceHitRemoteKey(remoteKey: HitRemoteKeyModel)
    suspend fun fetchHitRemoteKeyByQuery(query: String): HitRemoteKeyModel
    suspend fun deleteAllHitRemoteKeys()
    suspend fun <R> withTransaction(block: suspend () -> R): R
}