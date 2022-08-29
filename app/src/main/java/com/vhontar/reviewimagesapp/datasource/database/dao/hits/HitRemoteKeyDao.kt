package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vhontar.reviewimagesapp.datasource.database.entities.HitRemoteKeyDatabaseEntity

@Dao
interface HitRemoteKeyDao {
    @Query("SELECT * FROM remote_keys ORDER BY `query` DESC LIMIT 1")
    suspend fun fetchLastRemoteKey(): HitRemoteKeyDatabaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: HitRemoteKeyDatabaseEntity)

    @Query("SELECT * FROM remote_keys WHERE `query` = :query")
    suspend fun remoteKeyByQuery(query: String): HitRemoteKeyDatabaseEntity

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()
}