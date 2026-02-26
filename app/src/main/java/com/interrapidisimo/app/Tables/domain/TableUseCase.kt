package com.interrapidisimo.app.Tables.domain

import com.interrapidisimo.app.Tables.data.repository.TableRepository
import com.interrapidisimo.app.core.database.entity.TableEntity
import javax.inject.Inject

class TableUseCase @Inject constructor(
    private val repository: TableRepository
) {
    suspend operator fun invoke(user: String): Result<List<TableEntity>>{
        return repository.synchronize(user)
    }
}