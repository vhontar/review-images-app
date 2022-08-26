package com.vhontar.reviewimagesapp.data.network.response
import com.vhontar.reviewimagesapp.data.network.entities.HitNetworkEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitsResponse(
    @SerialName("hits")
    val hits: List<HitNetworkEntity>?,
    @SerialName("total")
    val total: Int?,
    @SerialName("totalHits")
    val totalHits: Int?
)