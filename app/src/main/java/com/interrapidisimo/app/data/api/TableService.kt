package com.interrapidisimo.app.data.api

import com.interrapidisimo.app.domain.model.table.TableResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface TableService {
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getSchema(
        @Header("Usuario") usuario: String
    ): List<TableResponse>
}