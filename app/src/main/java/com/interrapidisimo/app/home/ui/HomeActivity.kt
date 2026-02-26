package com.interrapidisimo.app.home.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.interrapidisimo.app.databinding.ActivityHomeBinding
import com.interrapidisimo.app.home.HomeViewModel
import com.interrapidisimo.app.home.domain.HomeState
import com.interrapidisimo.app.localidades.ui.LocalidadesActivity
import com.interrapidisimo.app.Tables.ui.TableActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity: ComponentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var currentUser: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect { state ->
                when(state){
                    is HomeState.Loading -> {}
                    is HomeState.UserLoaded ->{
                        currentUser = state.user.usuario
                        binding.tvUsuario.text = state.user.usuario
                        binding.tvIdentificacion.text = state.user.identificacion
                        binding.tvNombre.text = state.user.nombre
                    }
                    is HomeState.TablesLoaded -> {
                        Toast.makeText(
                            this@HomeActivity,
                            "Tablas sincronizadas",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(this@HomeActivity,TableActivity::class.java)
                        )
                    }
                    is HomeState.Error -> {
                        Toast.makeText(
                            this@HomeActivity,
                            state.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            }
        }
    }
}