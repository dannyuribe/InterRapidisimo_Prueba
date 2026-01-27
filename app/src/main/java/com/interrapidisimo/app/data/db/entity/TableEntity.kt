package com.interrapidisimo.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablas")
data class TableEntity (
    @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val nombreTabla: String,
        val tipo: String,
        val descripcion: String
)