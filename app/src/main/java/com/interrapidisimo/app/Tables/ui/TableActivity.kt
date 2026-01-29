package com.interrapidisimo.app.Tables.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.interrapidisimo.app.Tables.TableViewModel
import com.interrapidisimo.app.Tables.domain.TablesState
import com.interrapidisimo.app.databinding.ActivityTablesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class TableActivity : ComponentActivity() {
    private lateinit var binding: ActivityTablesBinding
    private val viewModel: TableViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTablesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()

        val user = intent.getStringExtra("user") ?: ""
        viewModel.sincronizar(user)
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is TablesState.Loading -> {}
                    is TablesState.Success -> {
                        if(state.list.isEmpty()){
                            Toast.makeText(
                                this@TableActivity,
                                "No hay tablas almacenadas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            binding.rvTablas.layoutManager =
                                LinearLayoutManager(this@TableActivity)
                            binding.rvTablas.adapter = TableAdapter(state.list)
                        }
                    }
                    is TablesState.Error -> {
                        Toast.makeText(
                            this@TableActivity,
                            state.message,
                            Toast.LENGTH_LONG
                            ).show()
                    }
                }
            }
        }
    }
}
