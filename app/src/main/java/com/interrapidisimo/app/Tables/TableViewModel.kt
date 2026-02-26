package com.interrapidisimo.app.Tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.Tables.data.repository.TableRepository
import com.interrapidisimo.app.Tables.domain.TablesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val repository: TableRepository
) : ViewModel() {

    private val _state = MutableStateFlow<TablesState>(TablesState.Loading)
    val state = _state.asStateFlow()

    private var syncJob: Job? = null

    fun synchronize(user: String) {
        syncJob?.cancel()

        syncJob = viewModelScope.launch {
            _state.update { TablesState.Loading }

            val result = repository.synchronize(user)

            result
                .onSuccess { list ->
                    _state.update { TablesState.Success(list) }
                }
                .onFailure { e ->
                    _state.update {
                        TablesState.Error(
                            e.message ?: "Error al sincronizar"
                        )
                    }
                }
        }
    }
}