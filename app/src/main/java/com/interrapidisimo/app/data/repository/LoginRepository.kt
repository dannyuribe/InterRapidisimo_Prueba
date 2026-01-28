package com.interrapidisimo.app.data.repository

import com.interrapidisimo.app.data.api.LoginService
import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.domain.model.login.LoginResponse

class LoginRepository(
    private val loginService: LoginService) {

    suspend fun login(request: LoginRequest): Result<LoginResponse> =
     try {
         val response = loginService.login(request)

         if(response.isSuccessful && response.body() != null){
             Result.success(response.body()!!)
         }else{
             Result.failure(Exception("Error servidor: ${response.code()}"))
         }
     }catch (e: Exception){
         Result.failure(e)
     }
}