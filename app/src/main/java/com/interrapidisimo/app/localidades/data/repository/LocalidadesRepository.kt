package com.interrapidisimo.app.localidades.data.repository

import com.interrapidisimo.app.localidades.data.api.LocalidadesService

class LocalidadesRepository(
    private val service: LocalidadesService
) {
    suspend fun getLocalidades() = service.getLocalidades()
}