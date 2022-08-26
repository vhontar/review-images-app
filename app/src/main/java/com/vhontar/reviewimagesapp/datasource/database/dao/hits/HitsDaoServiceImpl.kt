package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.datasource.database.entities.toDatabaseEntities
import com.vhontar.reviewimagesapp.datasource.database.entities.toDomainModels
import javax.inject.Inject

class HitsDaoServiceImpl @Inject constructor(
    private val dao: HitDao
): HitsDaoService {
    override fun fetchAll(): List<HitModel> = dao.fetchAll().toDomainModels()
    override fun insertAll(list: List<HitModel>) = dao.insertAll(list.toDatabaseEntities())
    override fun deleteAll() = dao.deleteAll()
}