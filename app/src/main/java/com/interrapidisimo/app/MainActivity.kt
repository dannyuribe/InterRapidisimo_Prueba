package com.interrapidisimo.app

import android.content.Intent
import com.interrapidisimo.app.data.api.RetrofitClient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.data.api.LoginService
import com.interrapidisimo.app.data.api.VersionService
import com.interrapidisimo.app.data.db.AppDatabase
import com.interrapidisimo.app.data.db.entity.UserEntity
import com.interrapidisimo.app.data.repository.LoginRepository
import com.interrapidisimo.app.data.repository.VersionRepository
import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.security.login.LoginManager
import com.interrapidisimo.app.security.version.VersionManager
import com.interrapidisimo.app.ui.home.HomeActivity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        validateVersion()
    }
    private fun validateVersion(){
        lifecycleScope.launch{
            try{
                val versionService = RetrofitClient.create(VersionService::class.java)
                val versionRepository = VersionRepository(versionService)
                val versionManager= VersionManager(versionRepository)

                val response = versionManager.checkVersion(this@MainActivity)

                response.onSuccess { message ->
                    handleVersionMessage(message)
                }.onFailure{ err ->
                    Toast.makeText(

                        this@MainActivity,
                        "Error:${err.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(this@MainActivity,
                    "Fallo de red: ${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginActivity(){
        lifecycleScope.launch {
            try {
                val loginService = RetrofitClient.create(LoginService::class.java)
                val loginRepository = LoginRepository(loginService)
                val loginManager = LoginManager(loginRepository)
                val lf = 0x0A.toChar()
                val request = LoginRequest(
                    Mac = "",
                    NomAplicacion = "Controller APP",
                    "SW50ZXIyMDIx${lf}",
                    Path = "",
                    Usuario = "cGFtLm1lcmVkeTIx${lf}"
                )

                var result = loginManager.login(request)

                result.onSuccess { res ->
                    val db = AppDatabase.get(this@MainActivity)

                    db.userDao().save(
                        UserEntity(
                            usuario = res.usuario ?: "",
                            identificacion = res.identificacion ?: "N/A",
                            nombre = res.nombre ?: "N/A"
                        )
                    )

                    val saved = db.userDao().getUser()
                    println("saved: ${saved}")

                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()
                }.onFailure {
                    Toast.makeText(
                        this@MainActivity,
                        it.message,
                        Toast.LENGTH_LONG)
                        .show()
                }
            }catch (e: Exception){
                Toast.makeText(this@MainActivity,
                    "Fallo de redd: ${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun handleVersionMessage(message: String){
        when{
            message.contains("INFERIOR") -> {
                proceedWithCaution(message)
            }
            message.contains("SUPERIOR") -> {
                proceedWithCaution(message)
            }
            message.contains("ACTUALIZADA") -> {
                proceedToLogin()
            }
            else -> {
                proceedToLogin()
            }
        }
    }

    private fun proceedToLogin() {
        Toast.makeText(this, "Continuando...", Toast.LENGTH_SHORT).show()
        loginActivity()
    }

    private fun proceedWithCaution(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}