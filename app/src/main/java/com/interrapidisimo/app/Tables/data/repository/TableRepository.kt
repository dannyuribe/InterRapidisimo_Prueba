package com.interrapidisimo.app.Tables.data.repository

import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.Tables.data.db.TableDao
import com.interrapidisimo.app.Tables.data.mapper.TableMapper
import com.interrapidisimo.app.core.database.entity.TableEntity
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val api: TableService,
    private val dao: TableDao,
    private val mapper: TableMapper
) {
    suspend fun synchronize(user: String): Result<List<TableEntity>> {
        return try {
            val response = api.getSchema(user)
            val entities = mapper.toEntity(response)

            dao.clear()
            dao.insertAll(entities)

            Result.success(dao.getAll())

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}