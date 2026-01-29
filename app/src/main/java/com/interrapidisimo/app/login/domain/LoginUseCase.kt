package com.interrapidisimo.app.login.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun login(userInput: String,passInput: String ) =
        authRepository.login(userInput, passInput)
}