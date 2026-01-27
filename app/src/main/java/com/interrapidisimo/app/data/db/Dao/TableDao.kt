package com.interrapidisimo.app.data.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interrapidisimo.app.data.db.entity.TableEntity

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tablas: List<TableEntity>)

    @Query("SELECT * FROM tablas")
    suspend fun getAll(): List<TableEntity>

    @Query("DELETE FROM tablas")
    suspend fun clear()
}