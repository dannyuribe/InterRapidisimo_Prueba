package com.interrapidisimo.app.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.interrapidisimo.app.databinding.ActivityLoginBinding
import com.interrapidisimo.app.home.ui.HomeActivity
import com.interrapidisimo.app.login.LoginViewModel
import com.interrapidisimo.app.login.domain.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity: ComponentActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPassword.text.toString()
            viewModel.login(user,pass)
        }
    }
    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
            when (state) {
                is LoginState.Idle -> {}
                is LoginState.Loading -> {}
                is LoginState.Success -> {
                    startActivity(Intent(
                        this@LoginActivity,
                        HomeActivity::class.java)
                    )
                    finish()
                }
                is LoginState.Error -> {
                    Toast.makeText(
                        this@LoginActivity,
                        state.message,
                        Toast.LENGTH_LONG).show()
                }

            }
        }
        }
    }

}