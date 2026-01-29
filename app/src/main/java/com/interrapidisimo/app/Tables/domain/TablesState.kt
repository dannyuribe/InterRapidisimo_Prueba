package com.interrapidisimo.app.Tables.domain

import com.interrapidisimo.app.core.database.entity.TableEntity

sealed class TablesState {
    object Loading : TablesState()
    data class Success(val  list: List<TableEntity>): TablesState()
    data class Error(val message: String): TablesState()
}