package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.AccountType
import com.huseynsharif.uikit.databinding.FragmentAddAccountBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddAccountBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddAccountBottomSheetBinding

    @Inject
    lateinit var accountDao: AccountDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAccountBottomSheetBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAccountTypesSpinner()
        initCurrencySpinners()

        binding.btnSave.setOnClickListener {
            saveAccount()
        }
    }

    private fun saveAccount() {
        val account = Account(
            binding.accountName.text.toString(),
            AccountType.valueOf(binding.accountType.selectedItem.toString().uppercase()),
            binding.currency.selectedItem.toString(),
            binding.amount.text.toString().toDouble()
        )

        CoroutineScope(Dispatchers.IO).launch {
            accountDao.insert(account)
        }

        // close bottom sheet
        this.dismiss()

    }

    private fun initCurrencySpinners() {
        val currency = resources.getStringArray(com.huseynsharif.common.R.array.currency_list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currency)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.currency.adapter = adapter
    }

    private fun initAccountTypesSpinner() {
        val accountTypes =
            resources.getStringArray(com.huseynsharif.common.R.array.account_type_icons_list)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, accountTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.accountType.adapter = adapter

    }

}