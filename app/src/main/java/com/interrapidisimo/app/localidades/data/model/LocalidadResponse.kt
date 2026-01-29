package com.interrapidisimo.app.localidades.data.model

import com.google.gson.annotations.SerializedName

data class LocalidadResponse(
    @SerializedName("IdLocalidad") val id: String,
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("NombreCorto") val nombreCorto: String?,
    @SerializedName("NombreAncestroPGrado") val nombreAncestroPGrado: String?,
    @SerializedName("NombreCompleto") val nombreCompleto: String,
    @SerializedName("AbreviacionCiudad") val abreviacion: String
)