package com.interrapidisimo.app.ui.login

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.data.api.LoginService
import com.interrapidisimo.app.data.api.RetrofitClient
import com.interrapidisimo.app.data.db.AppDatabase
import com.interrapidisimo.app.data.db.entity.UserEntity
import com.interrapidisimo.app.data.repository.LoginRepository
import com.interrapidisimo.app.databinding.ActivityLoginBinding
import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.security.login.LoginManager
import com.interrapidisimo.app.ui.home.HomeActivity
import kotlinx.coroutines.launch

class LoginActivity: ComponentActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPassword.text.toString()

            if(validateData(user,pass)){
                loginActivity(user, pass)
            }
        }
    }

    private fun replaceData(data: String): String{
        val lf = 0x0A.toChar()
        return data.replace("\\n","${lf}")
    }

    private fun validateData(user: String,pass: String): Boolean{
        if(user.isEmpty() || pass.isEmpty()){
            Toast.makeText(
                this@LoginActivity,
                "Debes ingresar los valores",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    private fun loginActivity(userInput: String,passInput: String ){
        lifecycleScope.launch {
            try {
                val loginService = RetrofitClient.create(LoginService::class.java)
                val loginRepository = LoginRepository(loginService)
                val loginManager = LoginManager(loginRepository)

                val request = LoginRequest(
                    Mac = "",
                    NomAplicacion = "Controller APP",
                    replaceData(passInput),
                    Path = "",
                    Usuario = replaceData(userInput)
                )

                println("---REQUEST____: ${request}")

                var result = loginManager.login(request)

                result.onSuccess { res ->
                    val db = AppDatabase.get(this@LoginActivity)

                    db.userDao().save(
                        UserEntity(
                            usuario = res.usuario ?: "",
                            identificacion = res.identificacion ?: "N/A",
                            nombre = res.nombre ?: "N/A"
                        )
                    )

                    val saved = db.userDao().getUser()
                    println("saved: ${saved}")

                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }.onFailure {
                    Toast.makeText(
                        this@LoginActivity,
                        it.message,
                        Toast.LENGTH_LONG)
                        .show()
                }
            }catch (e: Exception){
                Toast.makeText(this@LoginActivity,
                    "Fallo de redd: ${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

    }
}