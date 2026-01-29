package com.interrapidisimo.app.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("Usuario")
    val usuario: String?,

    @SerializedName("Identificacion")
    val identificacion: String?,

    @SerializedName("Nombre")
    val nombre: String?,

    @SerializedName("Apellido1")
    val apellido1: String?,

    @SerializedName("Apellido2")
    val apellido2: String?,

    @SerializedName("Cargo")
    val cargo: String?,

    @SerializedName("Aplicaciones")
    val aplicaciones: Any?,   // viene null o lista (luego lo tipamos)

    @SerializedName("Ubicaciones")
    val ubicaciones: Any?,

    @SerializedName("MensajeResultado")
    val mensajeResultado: Int?,

    @SerializedName("IdLocalidad")
    val idLocalidad: String?,

    @SerializedName("NombreLocalidad")
    val nombreLocalidad: String?,

    @SerializedName("NomRol")
    val nomRol: String?,

    @SerializedName("IdRol")
    val idRol: String?,

    @SerializedName("TokenJWT")
    val tokenJWT: String?,

    @SerializedName("ModulosApp")
    val modulosApp: Any?
)

