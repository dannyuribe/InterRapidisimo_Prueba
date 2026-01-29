package com.interrapidisimo.app.Tables.data.api

import com.interrapidisimo.app.Tables.data.model.TableResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface TableService {
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getSchema(
        @Header("Usuario") usuario: String
    ): List<TableResponse>
}