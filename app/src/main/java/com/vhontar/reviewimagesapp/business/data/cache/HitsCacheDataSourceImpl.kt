package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitsDaoService
import javax.inject.Inject

class HitsCacheDataSourceImpl @Inject constructor(
    private val hitsDaoService: HitsDaoService
): HitsCacheDataSource {
    override fun fetchAll(): List<HitModel> = hitsDaoService.fetchAll()
    override fun insertAll(list: List<HitModel>) = hitsDaoService.insertAll(list)
    override fun deleteAll() = hitsDaoService.deleteAll()
}