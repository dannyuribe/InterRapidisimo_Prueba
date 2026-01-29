package com.interrapidisimo.app.home.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.core.database.AppDatabase
import com.interrapidisimo.app.core.network.RetrofitClient
import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.databinding.ActivityHomeBinding
import com.interrapidisimo.app.home.HomeViewModel
import com.interrapidisimo.app.home.data.repository.HomeRepository
import com.interrapidisimo.app.home.domain.HomeState
import com.interrapidisimo.app.localidades.ui.LocalidadesActivity
import com.interrapidisimo.app.Tables.ui.TableActivity
import kotlinx.coroutines.launch

class HomeActivity: ComponentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var currentUser: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.get(this)
        val service = RetrofitClient.create(TableService::class.java)
        val repository = HomeRepository(db, service)
        viewModel = HomeViewModel(repository)

        observeState()
        viewModel.loadUser()

        binding.btnTablas.setOnClickListener {
            currentUser?.let { viewModel.syncTables(it) }
                ?: Toast.makeText(this, "Usuario no disponible", Toast.LENGTH_LONG).show()
        }
        binding.btnLocalidades.setOnClickListener {
            startActivity(Intent(this, LocalidadesActivity::class.java))
        }
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is HomeState.Loading -> {}
                    is HomeState.UserLoaded ->{
                        currentUser = state.user.usuario
                        binding.tvUsuario.text = state.user.usuario
                        binding.tvIdentificacion.text = state.user.identificacion
                        binding.tvNombre.text = state.user.nombre
                    }
                    is HomeState.TablesSynced -> {
                        Toast.makeText(this@HomeActivity, "Tablas sincronizadas", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@HomeActivity, TableActivity::class.java))
                    }
                    is HomeState.Error -> {
                        Toast.makeText(this@HomeActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}