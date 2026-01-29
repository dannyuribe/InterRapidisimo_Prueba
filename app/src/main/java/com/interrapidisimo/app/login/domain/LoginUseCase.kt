package com.interrapidisimo.app.login.domain

import com.interrapidisimo.app.login.data.db.UserDao
import com.interrapidisimo.app.core.database.entity.UserEntity
import com.interrapidisimo.app.login.data.repository.LoginRepository
import com.interrapidisimo.app.login.data.model.LoginRequest

class LoginUseCase(
    private val repository: LoginRepository,
    private val userDao: UserDao
) {
    private fun normalize(data: String): String{
        val lf = 0x0A.toChar()
        return data.replace("\\n","${lf}")
    }

    suspend fun login(userInput: String,passInput: String ): Result<Unit>{

        val request = LoginRequest(
            Mac = "",
            NomAplicacion = "Controller APP",
            normalize(passInput),
            Path = "",
            Usuario = normalize(userInput)
        )

        return repository.login(request).map{ res ->
            this.userDao.save(
                UserEntity(
                    usuario = res.usuario ?: "",
                    identificacion = res.identificacion ?: "N/A",
                    nombre = res.nombre ?: "N/A"
                )
            )
        }

    }
}