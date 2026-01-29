package com.interrapidisimo.app.login.data.model

data class LoginRequest(
    val Mac: String,
    val NomAplicacion: String,
    val Password: String,
    val Path: String,
    val Usuario: String
)
