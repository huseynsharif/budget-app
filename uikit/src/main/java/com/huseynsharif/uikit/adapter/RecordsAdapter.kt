package com.huseynsharif.uikit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.uikit.databinding.CardRecordBinding

class RecordsAdapter(
    private val context: Context
) : ListAdapter<Record, RecordsAdapter.RecordsViewHolder>(RecordsDiffCheck()) {
    inner class RecordsViewHolder(
        val binding: CardRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(record: Record) {
            binding.apply {
                binding.category.text = record.category.title
                binding.categoryIcon.setImageResource(getResourceIdByName(context, record.category.icon))
                binding.currency.text = record.account.currency
                binding.amount.text = record.amount.toString()
            }
        }

    }

    private class RecordsDiffCheck : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.createdAt == newItem.createdAt

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val view = CardRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}