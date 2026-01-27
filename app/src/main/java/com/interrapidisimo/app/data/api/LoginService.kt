package com.interrapidisimo.app.data.api

import com.interrapidisimo.app.domain.model.login.LoginRequest
import com.interrapidisimo.app.domain.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers(
        "Usuario:pam.meredy21",
        "Identificacion:987204545",
        "Accept:text/json",
        "IdUsuario:pam.meredy21",
        "IdCentroServicio:1295",
        "NombreCentroServicio:PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
        "IdAplicativoOrigen:9",
        "Content-Type:application/json"
    )
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun  login(@Body body: LoginRequest): Response<LoginResponse>
}