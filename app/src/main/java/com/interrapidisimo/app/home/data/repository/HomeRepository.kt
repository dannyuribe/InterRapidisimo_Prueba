package com.interrapidisimo.app.home.data.repository

import com.interrapidisimo.app.core.database.AppDatabase
import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.Tables.data.repository.TableRepository

class HomeRepository(
    private val db: AppDatabase,
    private val service: TableService
) {
    suspend fun getUser() = db.userDao().getUser()
    suspend fun  syncTables(user: String) =
        TableRepository(service,db.tablaDao())
            .sincronizar(user)
}