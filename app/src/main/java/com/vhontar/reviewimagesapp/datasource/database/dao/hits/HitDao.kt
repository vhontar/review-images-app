package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity

@Dao
interface HitDao {
    @Query("select * from hits")
    fun fetchAll(): List<HitDatabaseEntity>

    @Insert
    fun insertAll(hits: List<HitDatabaseEntity>)

    @Query("delete from hits")
    fun deleteAll()
}