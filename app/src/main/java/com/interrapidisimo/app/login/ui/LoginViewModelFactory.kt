package com.interrapidisimo.app.login.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interrapidisimo.app.login.data.api.LoginService
import com.interrapidisimo.app.core.network.RetrofitClient
import com.interrapidisimo.app.core.database.AppDatabase
import com.interrapidisimo.app.login.LoginViewModel
import com.interrapidisimo.app.login.data.repository.LoginRepository
import com.interrapidisimo.app.login.domain.LoginUseCase

class LoginViewModelFactory(private val context: Context)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){

            val  service = RetrofitClient.create(LoginService::class.java)
            val repository = LoginRepository(service)
            val db = AppDatabase.get(context)
            val useCase = LoginUseCase(repository, db.userDao())

            return LoginViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}