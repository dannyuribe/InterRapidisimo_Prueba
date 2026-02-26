package com.interrapidisimo.app.home.data.repository

import com.interrapidisimo.app.core.database.AppDatabase
import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.Tables.data.repository.TableRepository
import com.interrapidisimo.app.user.data.repository.UserRepository
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val userRepository: UserRepository,
    private val tableRepository: TableRepository
) {
    suspend fun getUser() = userRepository.getUser()
    suspend fun syncTables(user: String) = tableRepository.synchronize(user)
}