package com.huseynsharif.add.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.uikit.databinding.CardIconBinding

class IconsAdapter(
    private val context: Context
) : ListAdapter<Category, IconsAdapter.IconsViewHolder>(IconDiffCheck()) {

    inner class IconsViewHolder(
        private val binding: CardIconBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(icon: Category){
            binding.apply {
                binding.icon.setImageResource(getResourceIdByName(context, icon.title.lowercase()))
                binding.iconTitle.text = icon.title
            }
        }
    }

    private class IconDiffCheck : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.icon == newItem.icon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsViewHolder {
        val view = CardIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IconsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IconsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

}