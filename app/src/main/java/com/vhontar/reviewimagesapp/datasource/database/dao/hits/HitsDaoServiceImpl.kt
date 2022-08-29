package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitRemoteKeyModel
import com.vhontar.reviewimagesapp.datasource.database.AppDatabase
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntities
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModel
import javax.inject.Inject

class HitsDaoServiceImpl @Inject constructor(
    private val database: AppDatabase
) : HitsDaoService {
    override suspend fun fetchHit(id: Int): HitModel? = database.hitDao().fetch(id)?.toDomainModel()
    override fun fetchAllHits(): PagingSource<Int, HitDatabaseEntity> {
        return database.hitDao().fetchAll()
    }

    override suspend fun insertHit(model: HitModel) = database.hitDao().insert(model.toDatabaseEntity())
    override suspend fun insertAllHits(list: List<HitModel>) = database.hitDao().insertAll(list.toDatabaseEntities())
    override suspend fun deleteAllHits() = database.hitDao().deleteAll()
    override suspend fun fetchLastRemoteKey(): HitRemoteKeyModel? = database.hitRemoteKeyDao().fetchLastRemoteKey()?.toDomainModel()
    override suspend fun insertOrReplaceHitRemoteKey(remoteKey: HitRemoteKeyModel) =
        database.hitRemoteKeyDao().insertOrReplace(remoteKey.toDatabaseEntity())

    override suspend fun fetchHitRemoteKeyByQuery(query: String) =
        database.hitRemoteKeyDao().remoteKeyByQuery(query).toDomainModel()

    override suspend fun deleteAllHitRemoteKeys() {
        database.hitRemoteKeyDao().deleteAll()
    }

    override suspend fun <R> withTransaction(block: suspend () -> R) = database.withTransaction {
        block()
    }
}