package com.interrapidisimo.app.version.data.api

import retrofit2.Response
import retrofit2.http.GET

interface VersionService{
    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getVersion(): Response<String>
}