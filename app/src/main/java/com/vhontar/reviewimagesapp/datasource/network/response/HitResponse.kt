package com.vhontar.reviewimagesapp.datasource.network.response

import com.vhontar.reviewimagesapp.datasource.network.entities.HitNetworkEntity
import kotlinx.serialization.SerialName

data class HitResponse(
    @SerialName("hit") val hit: HitNetworkEntity?,
): DefaultNetworkResponse