package com.vhontar.reviewimagesapp.datasource.database.dao.hits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity

@Dao
interface HitDao {
    @Query("select * from hits where id = :id")
    fun fetch(id: Int): HitDatabaseEntity?

    @Query("select * from hits")
    fun fetchAll(): List<HitDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hit: HitDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hits: List<HitDatabaseEntity>)

    @Query("delete from hits")
    fun deleteAll()
}