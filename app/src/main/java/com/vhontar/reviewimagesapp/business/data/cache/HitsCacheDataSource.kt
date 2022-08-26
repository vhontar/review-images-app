package com.vhontar.reviewimagesapp.business.data.cache

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

interface HitsCacheDataSource {
    fun fetchAll(): List<HitModel>
    fun insertAll(list: List<HitModel>)
    fun deleteAll()
}