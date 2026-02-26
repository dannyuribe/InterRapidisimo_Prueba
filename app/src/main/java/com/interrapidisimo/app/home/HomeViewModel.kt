package com.interrapidisimo.app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.home.data.repository.HomeRepository
import com.interrapidisimo.app.home.domain.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()

    private var syncJob: Job? = null

    fun loadUser() {
        viewModelScope.launch {
            _state.update { HomeState.Loading }

            val user = repository.getUser()

            if (user == null) {
                _state.update { HomeState.Error("No hay usuario") }
            } else {
                _state.update { HomeState.UserLoaded(user) }
            }
        }
    }

    fun syncTables(user: String) {
        syncJob?.cancel()

        syncJob = viewModelScope.launch {
            _state.update { HomeState.Loading }

            val result = repository.syncTables(user)

            result
                .onSuccess { list ->
                    _state.update { HomeState.TablesLoaded(list) }
                }
                .onFailure { e ->
                    _state.update {
                        HomeState.Error(e.message ?: "Error al sincronizar")
                    }
                }
        }
    }
}