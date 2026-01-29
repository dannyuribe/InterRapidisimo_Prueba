package com.interrapidisimo.app.localidades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.localidades.data.repository.LocalidadesRepository
import com.interrapidisimo.app.localidades.domain.LocalidadesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LocalidadesViewModel(
    private val repository: LocalidadesRepository
): ViewModel() {
    private val _state = MutableStateFlow<LocalidadesState>(LocalidadesState.Loading)
    val state = _state.asStateFlow()

    fun loadLocalidades(){
        viewModelScope.launch {
            try {
                val response = repository.getLocalidades()
                if(response.isSuccessful && response.body() != null){
                    _state.value = LocalidadesState.Success(response.body()!!)
                }else{
                    _state.value = LocalidadesState.Error("Error: ${response.code()}")
                }
            }catch (e: Exception){
                _state.value = LocalidadesState.Error(e.message ?: "Error")
            }
        }
    }
}