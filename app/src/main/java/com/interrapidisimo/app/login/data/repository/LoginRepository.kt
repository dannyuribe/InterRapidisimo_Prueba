package com.interrapidisimo.app.login.data.repository

import com.interrapidisimo.app.login.data.api.LoginService
import com.interrapidisimo.app.login.data.model.LoginRequest
import com.interrapidisimo.app.login.data.model.LoginResponse

class LoginRepository(
    private val loginService: LoginService
) {

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