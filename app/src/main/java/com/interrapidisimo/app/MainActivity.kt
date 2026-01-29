package com.interrapidisimo.app

import android.content.Intent
import com.interrapidisimo.app.core.network.RetrofitClient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.version.data.api.VersionService
import com.interrapidisimo.app.version.data.repository.VersionRepository
import com.interrapidisimo.app.version.domain.VersionManager
import com.interrapidisimo.app.login.ui.LoginActivity
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
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
    }

    private fun proceedWithCaution(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}