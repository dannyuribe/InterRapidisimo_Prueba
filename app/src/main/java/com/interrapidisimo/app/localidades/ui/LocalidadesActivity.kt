package com.interrapidisimo.app.localidades.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.app.R
import com.interrapidisimo.app.core.network.RetrofitClient
import com.interrapidisimo.app.localidades.LocalidadesViewModel
import com.interrapidisimo.app.localidades.data.repository.LocalidadesRepository
import com.interrapidisimo.app.localidades.data.api.LocalidadesService
import com.interrapidisimo.app.localidades.domain.LocalidadesState
import com.interrapidisimo.app.localidades.ui.LocalidadesAdapter
import kotlinx.coroutines.launch

class LocalidadesActivity: ComponentActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: LocalidadesAdapter
    private lateinit var viewModel: LocalidadesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localidades)

        recycler = findViewById(R.id.recyclerLocalidades)
        recycler.layoutManager = LinearLayoutManager(this)

        val service = RetrofitClient.create(LocalidadesService::class.java)
        val repository = LocalidadesRepository(service)
        viewModel = LocalidadesViewModel(repository)

        observeState()

        viewModel.loadLocalidades()
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is LocalidadesState.Loading -> {}
                    is LocalidadesState.Success ->{
                        adapter = LocalidadesAdapter(state.list)
                        recycler.adapter = adapter
                    }
                    is LocalidadesState.Error -> {
                        Toast.makeText(
                            this@LocalidadesActivity,
                            state.message,
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}