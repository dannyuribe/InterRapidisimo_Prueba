package com.interrapidisimo.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.interrapidisimo.app.databinding.ActivityLoginBinding
import com.interrapidisimo.app.ui.home.HomeActivity

class LoginActivity: ComponentActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = LoginViewModelFactory(this).create(LoginViewModel::class.java)

        observeState()

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPassword.text.toString()
            viewModel.login(user,pass)
        }
    }



    private fun observeState() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is LoginState.Idle -> {}
                is LoginState.Loading -> {}
                is LoginState.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is LoginState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

}