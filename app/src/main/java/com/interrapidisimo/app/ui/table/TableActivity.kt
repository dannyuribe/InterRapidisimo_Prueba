package com.interrapidisimo.app.ui.table

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.interrapidisimo.app.data.db.AppDatabase
import com.interrapidisimo.app.databinding.ActivityTablesBinding
import kotlinx.coroutines.launch

class TableActivity : ComponentActivity() {
    private lateinit var binding: ActivityTablesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadTables()
    }

    private fun loadTables(){
        lifecycleScope.launch {
            try {
                val db = AppDatabase.get(this@TableActivity)
                val list = db.tablaDao().getAll()

                if(list.isEmpty()){
                    Toast.makeText(
                        this@TableActivity,
                        "No hay tablas almacenadas",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    binding.rvTablas.layoutManager = LinearLayoutManager(this@TableActivity)
                    binding.rvTablas.adapter = TableAdapter(list)
                }
            }catch (e: Exception){
                Toast.makeText(
                    this@TableActivity,
                    "Error cargando tablas: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}