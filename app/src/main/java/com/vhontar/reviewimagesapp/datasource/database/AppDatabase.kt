package com.vhontar.reviewimagesapp.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitDao
import com.vhontar.reviewimagesapp.datasource.database.dao.hits.HitRemoteKeyDao
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity
import com.vhontar.reviewimagesapp.datasource.database.entities.HitRemoteKeyDatabaseEntity

@Database(
    entities = [
        HitDatabaseEntity::class,
        HitRemoteKeyDatabaseEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hitDao(): HitDao
    abstract fun hitRemoteKeyDao(): HitRemoteKeyDao
}