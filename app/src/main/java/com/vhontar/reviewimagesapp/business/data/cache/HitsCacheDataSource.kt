package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

interface HitsCacheDataSource {
    fun fetch(id: Int): HitModel?
    fun fetchAll(): List<HitModel>
    fun insert(model: HitModel)
    fun insertAll(list: List<HitModel>)
    fun deleteAll()
}