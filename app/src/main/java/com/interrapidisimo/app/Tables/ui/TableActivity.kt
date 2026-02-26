package com.interrapidisimo.app.Tables.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

    private val tableAdapter = TableAdapter { table ->
        Toast.makeText(
            this,
            "Click en ${table.nombreTabla}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTablesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        observeState()

        val user = intent.getStringExtra("user") ?: ""
        viewModel.synchronize(user)
    }

    private fun setupRecycler() {
        binding.rvTablas.apply {
            layoutManager = LinearLayoutManager(this@TableActivity)
            adapter = tableAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {

                        is TablesState.Loading -> {
                            // Aquí podrías mostrar un ProgressBar
                        }

                        is TablesState.Success -> {
                            tableAdapter.submitList(state.list)

                            if (state.list.isEmpty()) {
                                Toast.makeText(
                                    this@TableActivity,
                                    "No hay tablas almacenadas",
                                    Toast.LENGTH_SHORT
                                ).show()
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
}