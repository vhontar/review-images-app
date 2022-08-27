package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import javax.inject.Inject

class HitsCacheDataSourceImpl @Inject constructor(
    private val service: HitsDaoService
): HitsCacheDataSource {
    override suspend fun fetch(id: Int): HitModel? = service.fetch(id)
    override suspend fun fetchAll(): List<HitModel> = service.fetchAll()
    override suspend fun insert(model: HitModel) = service.insert(model)
    override suspend fun insertAll(list: List<HitModel>) = service.insertAll(list)
    override suspend fun deleteAll() = service.deleteAll()
}