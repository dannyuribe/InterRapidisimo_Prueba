package com.interrapidisimo.app.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interrapidisimo.app.data.api.LoginService
import com.interrapidisimo.app.data.api.RetrofitClient
import com.interrapidisimo.app.data.db.AppDatabase
import com.interrapidisimo.app.data.repository.LoginRepository
import com.interrapidisimo.app.domain.useCase.LoginUseCase

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