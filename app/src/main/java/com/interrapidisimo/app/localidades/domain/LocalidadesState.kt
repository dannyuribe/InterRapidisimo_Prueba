package com.interrapidisimo.app.localidades.domain

import android.os.Message
import com.interrapidisimo.app.localidades.data.model.LocalidadResponse
import java.util.Objects

sealed class LocalidadesState {
    object Loading : LocalidadesState()
    data class Success(val list: List<LocalidadResponse>) : LocalidadesState()
    data class Error(val message: String) : LocalidadesState()
}