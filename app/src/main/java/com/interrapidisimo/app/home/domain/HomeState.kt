package com.interrapidisimo.app.home.domain

import com.interrapidisimo.app.core.database.entity.UserEntity

sealed class HomeState {
    object Loading : HomeState()
    data class UserLoaded(val user: UserEntity) : HomeState()
    object TablesSynced : HomeState()
    data class Error(val message: String) : HomeState()
}