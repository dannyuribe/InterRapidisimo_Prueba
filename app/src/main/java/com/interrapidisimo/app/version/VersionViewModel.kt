package com.interrapidisimo.app.version

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.app.version.domain.CheckVersionUseCase
import com.interrapidisimo.app.version.domain.model.VersionResult
import com.interrapidisimo.app.version.ui.VersionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VersionViewModel @Inject constructor(
    private val checkVersionUseCase: CheckVersionUseCase
) : ViewModel(){
    private val _state = MutableStateFlow<VersionUiState>(VersionUiState.Loading)
    val state = _state.asStateFlow()

    fun validateVersion() = viewModelScope.launch {
        val result = checkVersionUseCase()
        result.onSuccess { version  ->
            _state.value = when(version ){
                is VersionResult.Inferior -> VersionUiState.ShowWarning("Actualiza la app" )
                is VersionResult.Superior -> VersionUiState.ShowWarning("Versión no soportada" )
                is VersionResult.Update -> VersionUiState.GoToLogin
            }
        }.onFailure {
            _state.value = VersionUiState.Error(it.message ?: "Error")
        }
    }
}