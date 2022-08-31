package com.vhontar.reviewimagesapp.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitDao
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitLastQueryDao
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.HitLastQueryDatabaseEntity

@Database(
    entities = [
        HitDatabaseEntity::class,
        HitLastQueryDatabaseEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hitDao(): HitDao
    abstract fun hitLastQueryDao(): HitLastQueryDao
}