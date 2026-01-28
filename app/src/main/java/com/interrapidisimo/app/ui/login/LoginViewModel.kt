package com.interrapidisimo.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.domain.useCase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state

    fun login(user: String, pass: String){
        viewModelScope.launch {
            _state.value = LoginState.Loading
            val result = loginUseCase.login(user, pass)

            _state.value = result.fold(
                onSuccess = { LoginState.Success },
                onFailure = { LoginState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}