package com.interrapidisimo.app.Tables.data.repository

import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.Tables.data.db.TableDao
import com.interrapidisimo.app.core.database.entity.TableEntity

class TableRepository(
    private val api: TableService,
    private val dao: TableDao
) {
    suspend fun sincronizar(user: String): Result<List<TableEntity>> =
        try{
            val response = api.getSchema(user)

            val entities = response.map {
                TableEntity(
                    nombreTabla = it.nombreTabla ?: "",
                    tipo = it.tipo ?: "",
                    descripcion = it.descripcion ?: ""
                )
            }
            dao.clear()
            dao.insertAll(entities)

            val saved = dao.getAll()
            Result.success(saved)

        }catch(e: Exception){
            Result.failure(e)
        }
}