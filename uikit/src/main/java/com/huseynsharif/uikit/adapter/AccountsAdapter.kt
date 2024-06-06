package com.huseynsharif.uikit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.uikit.databinding.CardAccountBinding


class AccountsAdapter(
    private val context: Context,
    private val selectedAccount: String? = null,
    private val getPinned: ((Account) -> Unit)? = null,
    private val closeBottomSheet: (() -> Unit)? = null
) : ListAdapter<Account, AccountsAdapter.AccountsViewHolder>(AccountsAdapter.AccountDiffCheck()) {

    inner class AccountsViewHolder(
        private val binding: CardAccountBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(account: Account) {
            binding.apply {

                binding.accountTitle.text = account.name
                binding.currency.text = account.currency
                binding.balance.text = account.amount.toString()
                binding.accountIcon.setImageResource(
                    getResourceIdByName(
                        context, account.type.name.lowercase()
                    )
                )

                if (selectedAccount == account.name) {
                    binding.root.setBackgroundResource(com.huseynsharif.uikit.R.drawable.selected_account_background)
                }
                binding.root.setOnClickListener {
                    getPinned?.invoke(account)
                    closeBottomSheet?.invoke()
                }
            }
        }
    }

    private class AccountDiffCheck : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val view = CardAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}