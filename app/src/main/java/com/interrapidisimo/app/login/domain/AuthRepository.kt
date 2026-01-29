package com.interrapidisimo.app.login.domain

import com.interrapidisimo.app.login.data.model.LoginResponse

interface AuthRepository {
    suspend fun login(userInput: String, passInput: String): Result<LoginResponse>
}