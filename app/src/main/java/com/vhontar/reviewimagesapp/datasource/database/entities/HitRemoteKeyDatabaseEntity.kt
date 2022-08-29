package com.vhontar.reviewimagesapp.datasource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class HitRemoteKeyDatabaseEntity(
    @PrimaryKey val query: String,
    val nextKey: Int? = null
)