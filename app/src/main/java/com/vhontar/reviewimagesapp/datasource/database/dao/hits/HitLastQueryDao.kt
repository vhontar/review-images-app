package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vhontar.reviewimagesapp.datasource.database.entities.HitLastQueryDatabaseEntity

@Dao
interface HitLastQueryDao {
    @Query("SELECT * FROM last_query ORDER BY `query` DESC LIMIT 1")
    suspend fun fetchLastRemoteKey(): HitLastQueryDatabaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: HitLastQueryDatabaseEntity)

    @Query("SELECT * FROM last_query WHERE `query` = :query")
    suspend fun remoteKeyByQuery(query: String): HitLastQueryDatabaseEntity

    @Query("DELETE FROM last_query")
    suspend fun deleteAll()
}