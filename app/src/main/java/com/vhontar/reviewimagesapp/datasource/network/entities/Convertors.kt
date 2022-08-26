package com.vhontar.reviewimagesapp.datasource.network.entities

import com.vhontar.reviewimagesapp.business.domain.models.HitModel

internal fun HitNetworkEntity.toModel(): HitModel = HitModel(
    id = id ?: 0,
    userId = userId ?: 0,
    user = user ?: "",
    userImageURL = userImageURL ?: "",
    comments = comments ?: 0,
    downloads = downloads ?: 0,
    likes = likes ?: 0,
    views = views ?: 0,
    tags = tags ?: "",
    type = type ?: "",
    imageURL = imageURL ?: "",
    imageWidth = imageWidth ?: 0,
    imageHeight = imageHeight ?: 0,
    imageSize = imageSize ?: 0,
    previewURL = previewURL ?: "",
    previewWidth = previewWidth ?: 0,
    previewHeight = previewHeight ?: 0
)
internal fun List<HitNetworkEntity>?.toModels(): List<HitModel> = this?.map { it.toModel() } ?: listOf()