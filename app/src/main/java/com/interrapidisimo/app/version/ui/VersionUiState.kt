package com.interrapidisimo.app.version.ui

sealed class VersionUiState {
    object Loading: VersionUiState()
    object  GoToLogin: VersionUiState()
    data class  ShowWarning(val message: String): VersionUiState()
    data class  Error(val message: String): VersionUiState()
}