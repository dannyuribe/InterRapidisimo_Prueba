package com.interrapidisimo.app.home.domain

import com.interrapidisimo.app.core.database.entity.TableEntity
import com.interrapidisimo.app.core.database.entity.UserEntity

sealed class HomeState {
    object Loading : HomeState()
    data class UserLoaded(val user: UserEntity): HomeState()
    data class TablesLoaded(val tables: List<TableEntity>): HomeState()
    data class Error(val message: String): HomeState()
}