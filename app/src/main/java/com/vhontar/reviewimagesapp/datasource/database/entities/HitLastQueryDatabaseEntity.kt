package com.vhontar.reviewimagesapp.datasource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_query")
data class HitLastQueryDatabaseEntity(
    @PrimaryKey val query: String
)