package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntities
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModel
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModels
import javax.inject.Inject

class HitsDaoServiceImpl @Inject constructor(
    private val dao: HitDao
): HitsDaoService {
    override suspend fun fetch(id: Int): HitModel? = dao.fetch(id)?.toDomainModel()
    override suspend fun fetchAll(): List<HitModel> = dao.fetchAll().toDomainModels()
    override suspend fun insert(model: HitModel) = dao.insert(model.toDatabaseEntity())
    override suspend fun insertAll(list: List<HitModel>) = dao.insertAll(list.toDatabaseEntities())
    override suspend fun deleteAll() = dao.deleteAll()
}