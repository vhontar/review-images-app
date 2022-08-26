package com.vhontar.reviewimagesapp.data.network.entities

import com.vhontar.reviewimagesapp.domain.models.HitModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitEntity(
    @SerialName("id")
    val id: Int?,
    @SerialName("user_id")
    val userId: Int?,
    @SerialName("user")
    val user: String?,
    @SerialName("userImageURL")
    val userImageURL: String?,
    @SerialName("comments")
    val comments: Int?,
    @SerialName("downloads")
    val downloads: Int?,
    @SerialName("likes")
    val likes: Int?,
    @SerialName("views")
    val views: Int?,
    @SerialName("tags")
    val tags: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("imageHeight")
    val imageHeight: Int?,
    @SerialName("imageSize")
    val imageSize: Int?,
    @SerialName("imageURL")
    val imageURL: String?,
    @SerialName("imageWidth")
    val imageWidth: Int?,
    @SerialName("previewHeight")
    val previewHeight: Int?,
    @SerialName("previewURL")
    val previewURL: String?,
    @SerialName("previewWidth")
    val previewWidth: Int?,
)