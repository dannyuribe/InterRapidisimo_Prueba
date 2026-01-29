package com.interrapidisimo.app.Tables.data.model

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("NombreTabla")
        val nombreTabla: String?,
    @SerializedName("Tipo")
        val tipo: String?,
    @SerializedName("Descripcion")
        val descripcion: String?
)