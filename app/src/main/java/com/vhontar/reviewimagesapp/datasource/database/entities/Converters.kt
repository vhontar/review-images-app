package com.vhontar.reviewimagesapp.datasource.database.entities

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.models.HitLastQueryModel

internal fun HitDatabaseEntity.toDomainModel(): HitModel = HitModel(
    id = id,
    userId = userId,
    user = user,
    userImageURL = userImageURL,
    comments = comments,
    downloads = downloads,
    likes = likes,
    views = views,
    tags = tags,
    type = type,
    imageURL = imageURL,
    imageWidth = imageWidth,
    imageHeight = imageHeight,
    imageSize = imageSize,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight
)

internal fun List<HitDatabaseEntity>.toDomainModels(): List<HitModel> = map { it.toDomainModel() }

internal fun HitModel.toDatabaseEntity(): HitDatabaseEntity = HitDatabaseEntity(
    id = id,
    userId = userId,
    user = user,
    userImageURL = userImageURL,
    comments = comments,
    downloads = downloads,
    likes = likes,
    views = views,
    tags = tags,
    type = type,
    imageURL = imageURL,
    imageWidth = imageWidth,
    imageHeight = imageHeight,
    imageSize = imageSize,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight
)

internal fun List<HitModel>.toDatabaseEntities(): List<HitDatabaseEntity> = map { it.toDatabaseEntity() }

internal fun HitLastQueryDatabaseEntity.toDomainModel() = HitLastQueryModel(
    query = query
)

internal fun HitLastQueryModel.toDatabaseEntity() = HitLastQueryDatabaseEntity(
    query = query
)

