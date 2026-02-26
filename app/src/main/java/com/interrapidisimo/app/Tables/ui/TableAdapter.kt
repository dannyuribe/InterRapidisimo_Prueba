package com.interrapidisimo.app.Tables.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.app.core.database.entity.TableEntity
import com.interrapidisimo.app.databinding.ItemTableBinding

class TableAdapter(
    private val onClick: (TableEntity) -> Unit
) : ListAdapter<TableEntity, TableAdapter.TableViewHolder>(DiffCallback) {

    inner class TableViewHolder(
        private val binding: ItemTableBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TableEntity) {
            binding.tvTableName.text = item.nombreTabla
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = ItemTableBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TableEntity>() {
            override fun areItemsTheSame(
                oldItem: TableEntity,
                newItem: TableEntity
            ): Boolean = oldItem.nombreTabla == newItem.nombreTabla

            override fun areContentsTheSame(
                oldItem: TableEntity,
                newItem: TableEntity
            ): Boolean = oldItem == newItem
        }
    }
}