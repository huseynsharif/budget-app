package com.huseynsharif.uikit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.AccountType
import com.huseynsharif.uikit.adapter.AccountsAdapter
import com.huseynsharif.uikit.databinding.FragmentAccountListBottomSheetBinding


class AccountListBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAccountListBottomSheetBinding
    private lateinit var adapter: AccountsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountListBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        adapter = AccountsAdapter(requireContext())
        binding.accounts.adapter = adapter

        val accounts = mutableListOf<Account>(
            Account("Huseyn", AccountType.CASH, "USD", 4.5, 4.5),
            Account("User", AccountType.VISA, "AZN", 4.5, 4.5),
            Account("Admin", AccountType.MASTERCARD, "TRY", 43.4, 4.5)
        )

        adapter.submitList(accounts)

    }


}