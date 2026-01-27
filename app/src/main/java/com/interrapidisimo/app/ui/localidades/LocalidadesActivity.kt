package com.interrapidisimo.app.ui.localidades

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.app.R
import com.interrapidisimo.app.data.api.RetrofitClient
import com.interrapidisimo.app.data.api.LocalidadesService
import kotlinx.coroutines.launch

class LocalidadesActivity: ComponentActivity() {

    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localidades)

        recycler = findViewById(R.id.recyclerLocalidades)
        recycler.layoutManager = LinearLayoutManager(this)

        cargarLocalidades()
    }

    private fun cargarLocalidades() {
        lifecycleScope.launch {
            try {
                val service = RetrofitClient.create(LocalidadesService::class.java)
                val response = service.getLocalidades()

                if (response.isSuccessful && response.body() != null) {
                    val lista = response.body()!!

                    if (lista.isNotEmpty()) {
                        val adapter = LocalidadesAdapter(lista)
                        recycler.adapter = adapter
                    } else {
                        Toast.makeText(
                            this@LocalidadesActivity,
                            "No hay localidades disponibles",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LocalidadesActivity,
                        "Error en la respuesta: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@LocalidadesActivity,
                    "Error al consumir la API: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}