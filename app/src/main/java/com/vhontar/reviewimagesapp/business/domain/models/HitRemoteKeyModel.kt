package com.vhontar.reviewimagesapp.business.domain.models

data class HitRemoteKeyModel(
    val query: String,
    val nextKey: Int? = null
)