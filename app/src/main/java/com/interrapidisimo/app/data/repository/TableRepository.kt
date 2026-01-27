package com.interrapidisimo.app.data.repository

import com.interrapidisimo.app.data.api.TableService
import com.interrapidisimo.app.data.db.Dao.TableDao
import com.interrapidisimo.app.data.db.entity.TableEntity

class TableRepository(
    private val api: TableService,
    private val dao: TableDao
) {
    suspend fun sincronizar(user: String): Result<Unit> =
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

            Result.success(Unit)

        }catch(e: Exception){
            Result.failure(e)
        }
}
