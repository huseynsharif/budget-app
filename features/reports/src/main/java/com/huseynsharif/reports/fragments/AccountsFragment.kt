package com.huseynsharif.reports.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.reports.databinding.FragmentAccountBinding
import com.huseynsharif.reports.viewModels.accounts.AccountsEffect
import com.huseynsharif.reports.viewModels.accounts.AccountsEvent
import com.huseynsharif.reports.viewModels.accounts.AccountsState
import com.huseynsharif.reports.viewModels.accounts.AccountsViewModel
import com.huseynsharif.uikit.AddAccountBottomSheet
import com.huseynsharif.uikit.adapter.AccountsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject


@AndroidEntryPoint
class AccountsFragment :
    BaseFragment<FragmentAccountBinding, AccountsViewModel, AccountsState, AccountsEffect, AccountsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding =
        { inflater, viewGroup, value ->
            FragmentAccountBinding.inflate(inflater, viewGroup, value)
        }

    override fun getViewModelClass() = AccountsViewModel::class.java

    private lateinit var adapter: AccountsAdapter

    @Inject
    lateinit var accountDao: AccountDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAccountsAdapter()

        binding.addAccount.setOnClickListener {
            initAddAccountBottomSheet()
        }

        calculateTotal()
    }

    private fun calculateTotal() {
        val decimalFormat = DecimalFormat("#.##")
        lifecycleScope.launch{
            viewModel.getTotalBalance().collectLatest {
                binding.totalBalance.text = decimalFormat.format(it)
            }
        }
    }

    private fun initAddAccountBottomSheet() {
        val bottomSheet = AddAccountBottomSheet()
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }

    private fun initAccountsAdapter() {
        adapter = AccountsAdapter(requireContext())
        binding.accountList.adapter = adapter

        val accounts = viewModel.getAllAccounts()

        lifecycleScope.launch {
            accounts.collect{list ->
                adapter.submitList(list)
            }
        }
    }


}