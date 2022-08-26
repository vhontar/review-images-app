package com.vhontar.reviewimagesapp.datasource.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hits")
data class HitDatabaseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name ="user_id") val userId: Int,
    @ColumnInfo val user: String,
    @ColumnInfo(name ="user_image_url") val userImageURL: String,
    @ColumnInfo val comments: Int,
    @ColumnInfo val downloads: Int,
    @ColumnInfo val likes: Int,
    @ColumnInfo val views: Int,
    @ColumnInfo val tags: String,
    @ColumnInfo val type: String,
    @ColumnInfo(name ="image_url") val imageURL: String,
    @ColumnInfo(name ="image_width") val imageWidth: Int,
    @ColumnInfo(name ="image_height") val imageHeight: Int,
    @ColumnInfo(name ="image_size") val imageSize: Int,
    @ColumnInfo(name ="preview_url") val previewURL: String,
    @ColumnInfo(name ="preview_width") val previewWidth: Int,
    @ColumnInfo(name ="preview_height") val previewHeight: Int
)
