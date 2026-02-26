package com.interrapidisimo.app.user.data.repository

import com.interrapidisimo.app.core.database.AppDatabase
import javax.inject.Inject

class UserRepository @Inject constructor( private val db: AppDatabase ) {
    suspend fun getUser() = db.userDao().getUser()
}