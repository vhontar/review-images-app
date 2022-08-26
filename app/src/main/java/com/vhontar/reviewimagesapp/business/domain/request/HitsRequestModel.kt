package com.vhontar.reviewimagesapp.business.domain.request

data class HitsRequestModel(
    val query: String,
    val page: Int,
    val perPage: Int
)