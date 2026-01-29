package com.interrapidisimo.app.Tables.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interrapidisimo.app.core.database.entity.TableEntity

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(tablas: List<TableEntity>)

    @Query("SELECT * FROM tablas")
    suspend fun getAll(): List<TableEntity>

    @Query("DELETE FROM tablas")
    suspend fun clear()
}