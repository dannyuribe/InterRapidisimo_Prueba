package com.interrapidisimo.app.Tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.Tables.data.repository.TableRepository
import com.interrapidisimo.app.Tables.domain.TablesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TableViewModel(
    private val repository: TableRepository
): ViewModel() {
    private val _state = MutableStateFlow<TablesState>(TablesState.Loading)
    val state = _state.asStateFlow()

    fun sincronizar(user: String) {
        _state.value = TablesState.Loading

        viewModelScope.launch {
            val result = repository.sincronizar(user)

            result
                .onSuccess { list ->
                    _state.value = TablesState.Success(list)
                }
                .onFailure { e ->
                    _state.value = TablesState.Error(
                        e.message ?: "Error al sincronizar"
                    )
                }
        }
    }
}