package com.interrapidisimo.app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.home.data.repository.HomeRepository
import com.interrapidisimo.app.home.domain.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
): ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()

    fun loadUser(){
        viewModelScope.launch {
            val user = repository.getUser()
            if (user == null){
                _state.value = HomeState.Error("No hay usuario")
            }else{
                _state.value = HomeState.UserLoaded(user)
            }
        }
    }

    fun syncTables(user: String){
        viewModelScope.launch {
            val result = repository.syncTables(user)
            result.onSuccess {
                _state.value = HomeState.TablesSynced
            }.onFailure {
                _state.value = HomeState.Error(it.message ?: "Error")
            }
        }
    }
}