package com.vhontar.reviewimagesapp.domain.models

data class HitModel(
    val id: Int,
    val userId: Int,
    val user: String,
    val userImageURL: String,
    val comments: Int,
    val downloads: Int,
    val likes: Int,
    val views: Int,
    val tags: String,
    val type: String,
    val imageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int
)
