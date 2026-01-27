package com.interrapidisimo.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.data.api.RetrofitClient
import com.interrapidisimo.app.data.api.TableService
import com.interrapidisimo.app.data.db.AppDatabase
import com.interrapidisimo.app.data.repository.TableRepository
import com.interrapidisimo.app.databinding.ActivityHomeBinding
import com.interrapidisimo.app.ui.table.TableActivity
import kotlinx.coroutines.launch

class HomeActivity: ComponentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var currentUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUser()

        binding.btnTablas.setOnClickListener { tablas() }
        binding.btnLocalidades.setOnClickListener { localidades() }
    }

    private fun loadUser() {
        lifecycleScope.launch {
            val db = AppDatabase.get(this@HomeActivity)
            val user = db.userDao().getUser()
            if(user == null){
                Toast.makeText(
                    this@HomeActivity,
                    "No hay usuario",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                currentUser = user.usuario
                binding.tvUsuario.text = user.usuario
                binding.tvIdentificacion.text = user.identificacion
                binding.tvNombre.text = user.nombre
            }
        }
    }

    private fun tablas(){
        val user = currentUser
        if(user.isNullOrEmpty()){
            Toast.makeText(
                this,
                "Usuario no disponible",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        lifecycleScope.launch {
            try {

                val db = AppDatabase.get(this@HomeActivity)
                val service = RetrofitClient.create(TableService::class.java)
                val repository = TableRepository(service, db.tablaDao())

                val result = repository.sincronizar("usuario")

                result.onSuccess {
                    Toast.makeText(
                        this@HomeActivity,
                        "Tablas sincronizadas",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@HomeActivity, TableActivity::class.java))
                }

                result.onFailure {
                    Toast.makeText(this@HomeActivity, it.message, Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){
                Toast.makeText(
                    this@HomeActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun localidades(){
        val intent = Intent(
            this@HomeActivity,
            com.interrapidisimo.app.ui.localidades.LocalidadesActivity::class.java)
        startActivity(intent)
    }
}