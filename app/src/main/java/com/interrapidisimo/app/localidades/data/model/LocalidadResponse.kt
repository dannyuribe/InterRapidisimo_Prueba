package com.interrapidisimo.app.localidades.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocalidadResponse(

    @Json(name = "IdLocalidad")
    val id: String,

    @Json(name = "Nombre")
    val nombre: String,

    @Json(name = "NombreCorto")
    val nombreCorto: String?,

    @Json(name = "NombreAncestroPGrado")
    val nombreAncestroPGrado: String?,

    @Json(name = "NombreCompleto")
    val nombreCompleto: String,

    @Json(name = "AbreviacionCiudad")
    val abreviacion: String
)