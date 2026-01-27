package com.interrapidisimo.app.domain.model.table

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("NombreTabla")
        val nombreTabla: String?,
    @SerializedName("Tipo")
        val tipo: String?,
    @SerializedName("Descripcion")
        val descripcion: String?
)
