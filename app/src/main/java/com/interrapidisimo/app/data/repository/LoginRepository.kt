package com.interrapidisimo.app.data.repository

import com.interrapidisimo.app.data.api.LoginService
import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.domain.model.login.LoginResponse

class LoginRepository(
    private val service: LoginService
) {
    suspend fun  login(request: LoginRequest): Result<LoginResponse>{
        return try {

            val response = service.login(request)
println("LLLL------>>>>: ${response}")
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Error servidor: ${response.code()}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}