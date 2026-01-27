package com.interrapidisimo.app.domain.model.login

data class LoginRequest(
    val Mac: String,
    val NomAplicacion: String,
    val Password: String,
    val Path: String,
    val Usuario: String
)
