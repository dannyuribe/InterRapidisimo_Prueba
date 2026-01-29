package com.interrapidisimo.app.Tables.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.app.core.database.entity.TableEntity
import com.interrapidisimo.app.R

class TableAdapter(
    private val list: List<TableEntity>
) : RecyclerView.Adapter<TableAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv = view.findViewById<TextView>(R.id.tvTableName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = list[position].nombreTabla
    }

    override fun getItemCount() = list.size
}