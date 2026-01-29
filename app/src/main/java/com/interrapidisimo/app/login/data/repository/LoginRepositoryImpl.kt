package com.interrapidisimo.app.login.data.repository

import com.interrapidisimo.app.core.database.entity.UserEntity
import com.interrapidisimo.app.login.data.api.LoginService
import com.interrapidisimo.app.login.data.db.UserDao
import com.interrapidisimo.app.login.data.model.LoginRequest
import com.interrapidisimo.app.login.data.model.LoginResponse
import com.interrapidisimo.app.login.domain.AuthRepository

class LoginRepositoryImpl(
    private val loginService: LoginService,
    private val userDao: UserDao
): AuthRepository {

    override suspend fun login(userInput: String, passInput: String): Result<LoginResponse> {
        return try {

            val request = LoginRequest(
                Mac = "",
                NomAplicacion = "Controller APP",
                normalize(passInput),
                Path = "",
                Usuario = normalize(userInput)
            )

            val response = loginService.login(request)

            if (response.isSuccessful && response.body() != null) {
                val res = response.body()!!
                userDao.save(
                    UserEntity(
                        usuario = res.usuario ?: "",
                        identificacion = res.identificacion ?: "N/A",
                        nombre = res.nombre ?: "N/A"
                    )
                )
                Result.success(res)
            } else {
                Result.failure(Exception("Error servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun normalize(data: String): String{
        val lf = 0x0A.toChar()
        return data.replace("\\n","${lf}")
    }
}