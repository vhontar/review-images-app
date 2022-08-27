package com.vhontar.reviewimagesapp.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitDao
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity

@Database(
    entities = [
        HitDatabaseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hitDao(): HitDao
}