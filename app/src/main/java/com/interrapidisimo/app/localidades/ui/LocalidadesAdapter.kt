package com.interrapidisimo.app.localidades.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.app.R
import com.interrapidisimo.app.localidades.data.model.LocalidadResponse

class LocalidadesAdapter(
    private val list: List<LocalidadResponse>
) : RecyclerView.Adapter<LocalidadesAdapter.ViewHolder>(){
    inner class  ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvAbreviacion: TextView = view.findViewById(R.id.tvAbreviacion)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_localidad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val localidad = list[position]
        holder.tvAbreviacion.text = localidad.abreviacion
        holder.tvNombre.text = localidad.nombreCompleto
    }

    override fun getItemCount() = list.size
}