package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.room.withTransaction
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitLastQueryModel
import com.vhontar.reviewimagesapp.datasource.database.AppDatabase
import com.vhontar.reviewimagesapp.datasource.database.entities.*
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntities
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModel
import javax.inject.Inject

class HitsDaoServiceImpl @Inject constructor(
    private val database: AppDatabase
) : HitsDaoService {
    override suspend fun fetchHit(id: Int): HitModel? = database.hitDao().fetch(id)?.toDomainModel()
    override suspend fun fetchAllHits(): List<HitModel>? {
        return database.hitDao().fetchAll()?.toDomainModels()
    }

    override suspend fun insertHit(model: HitModel) = database.hitDao().insert(model.toDatabaseEntity())
    override suspend fun insertAllHits(list: List<HitModel>) = database.hitDao().insertAll(list.toDatabaseEntities())
    override suspend fun deleteAllHits() = database.hitDao().deleteAll()
    override suspend fun fetchLastQuery(): HitLastQueryModel? = database.hitLastQueryDao().fetchLastRemoteKey()?.toDomainModel()
    override suspend fun insertLastQuery(remoteKey: HitLastQueryModel) =
        database.hitLastQueryDao().insertOrReplace(remoteKey.toDatabaseEntity())

    override suspend fun deleteLastQuery() {
        database.hitLastQueryDao().deleteAll()
    }

    override suspend fun <R> withTransaction(block: suspend () -> R) = database.withTransaction {
        block()
    }
}