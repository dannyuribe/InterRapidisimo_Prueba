package com.interrapidisimo.app.Tables.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TableResponse(
    @Json(name = "NombreTabla")
        val nombreTabla: String?,
    @Json(name = "Tipo")
    val tipo: String?,

    @Json(name = "Descripcion")
        val descripcion: String?
)