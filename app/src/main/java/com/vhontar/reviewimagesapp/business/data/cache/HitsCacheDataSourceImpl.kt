package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import javax.inject.Inject

class HitsCacheDataSourceImpl @Inject constructor(
    private val service: HitsDaoService
): HitsCacheDataSource {
    override fun fetch(id: Int): HitModel? = service.fetch(id)
    override fun fetchAll(): List<HitModel> = service.fetchAll()
    override fun insert(model: HitModel) = service.insert(model)
    override fun insertAll(list: List<HitModel>) = service.insertAll(list)
    override fun deleteAll() = service.deleteAll()
}