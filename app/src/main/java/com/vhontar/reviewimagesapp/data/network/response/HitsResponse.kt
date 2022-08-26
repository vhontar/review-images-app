package com.vhontar.reviewimagesapp.data.network.response
import com.vhontar.reviewimagesapp.data.network.entities.HitEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitsResponse(
    @SerialName("hits")
    val hits: List<HitEntity?>?,
    @SerialName("total")
    val total: Int?,
    @SerialName("totalHits")
    val totalHits: Int?
)