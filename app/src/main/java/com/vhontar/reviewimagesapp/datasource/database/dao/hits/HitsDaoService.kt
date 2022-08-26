package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

interface HitsDaoService {
    fun fetchAll(): List<HitModel>
    fun insertAll(list: List<HitModel>)
    fun deleteAll()
}