package com.interrapidisimo.app.Tables.data.mapper

import com.interrapidisimo.app.Tables.data.model.TableResponse
import com.interrapidisimo.app.core.database.entity.TableEntity
import javax.inject.Inject

class TableMapper @Inject constructor() {
    fun toEntity(list: List<TableResponse>): List<TableEntity>{
        return list.map{
            TableEntity(
                nombreTabla = it.nombreTabla ?: "",
                tipo = it.tipo ?: "",
                descripcion = it.descripcion ?: ""
            )
        }
    }
}