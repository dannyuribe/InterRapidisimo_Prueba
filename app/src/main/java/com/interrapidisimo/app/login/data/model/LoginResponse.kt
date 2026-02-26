package com.interrapidisimo.app.login.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(

    @Json(name = "Usuario")
    val usuario: String?,

    @Json(name = "Identificacion")
    val identificacion: String?,

    @Json(name = "Nombre")
    val nombre: String?,

    @Json(name = "Apellido1")
    val apellido1: String?,

    @Json(name = "Apellido2")
    val apellido2: String?,

    @Json(name = "Cargo")
    val cargo: String?,

    @Json(name = "Aplicaciones")
    val aplicaciones: List<Any>?,

    @Json(name = "Ubicaciones")
    val ubicaciones: List<Any>?,

    @Json(name = "MensajeResultado")
    val mensajeResultado: Int?,

    @Json(name = "IdLocalidad")
    val idLocalidad: String?,

    @Json(name = "NombreLocalidad")
    val nombreLocalidad: String?,

    @Json(name = "NomRol")
    val nomRol: String?,

    @Json(name = "IdRol")
    val idRol: String?,

    @Json(name = "TokenJWT")
    val tokenJWT: String?,

    @Json(name = "ModulosApp")
    val modulosApp: List<Any>?
)