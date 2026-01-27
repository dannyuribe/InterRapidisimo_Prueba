package com.interrapidisimo.app.security.login

import com.interrapidisimo.app.data.repository.LoginRepository
import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.domain.model.login.LoginResponse

class LoginManager(
    private val repository: LoginRepository
) {
    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return repository.login(request)
    }
}