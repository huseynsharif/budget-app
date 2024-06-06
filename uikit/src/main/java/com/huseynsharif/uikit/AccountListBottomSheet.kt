package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.uikit.adapter.AccountsAdapter
import com.huseynsharif.uikit.databinding.FragmentAccountListBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AccountListBottomSheet(
    private val selectedCategory: String,
    private val getPinned: (Account) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAccountListBottomSheetBinding
    private lateinit var adapter: AccountsAdapter

    @Inject
    lateinit var accountDao: AccountDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountListBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        binding.btnAddAccount.setOnClickListener{
            val bottomSheet = AddAccountBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }

    private fun initAdapter() {
        adapter = AccountsAdapter(requireContext(), selectedCategory, getPinned) {
            this.dismiss()
        }
        binding.accounts.adapter = adapter

        val accounts = accountDao.getAll()

        lifecycleScope.launch {
            accounts.collect{list ->
                adapter.submitList(list)
            }
        }

    }
}