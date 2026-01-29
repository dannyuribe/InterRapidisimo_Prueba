package com.interrapidisimo.app.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.login.domain.LoginState
import com.interrapidisimo.app.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState.Loading)
    val state = _state.asStateFlow()

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