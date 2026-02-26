package com.interrapidisimo.app.version.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.login.ui.LoginActivity
import com.interrapidisimo.app.version.VersionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VersionActivity: ComponentActivity() {
    private val viewModel: VersionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
        viewModel.validateVersion()
    }
    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is VersionUiState.Loading -> {}
                    is VersionUiState.ShowWarning -> {
                        Toast.makeText(this@VersionActivity,
                            state.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is VersionUiState.GoToLogin -> {
                        Toast.makeText(this@VersionActivity,
                            "Version correcta Iniciando...",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(
                            this@VersionActivity,
                            LoginActivity::class.java)
                        )
                    }
                    is VersionUiState.Error -> {
                        Toast.makeText(this@VersionActivity,
                            state.message,
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}