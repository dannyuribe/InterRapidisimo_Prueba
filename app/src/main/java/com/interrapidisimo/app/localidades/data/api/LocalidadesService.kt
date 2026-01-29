package com.interrapidisimo.app.localidades.data.api

import com.interrapidisimo.app.localidades.data.model.LocalidadResponse
import retrofit2.Response
import retrofit2.http.GET

interface LocalidadesService {
    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun getLocalidades(): Response<List<LocalidadResponse>>
}