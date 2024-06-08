package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class AddAccountBottomSheet(
    private val selectedAccount: Account?=null
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddAccountBottomSheetBinding

    @Inject
    lateinit var accountDao: AccountDao

    var onDismiss : (()->Unit) =  {

    }

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
        fill()
        binding.btnSave.setOnClickListener {
            saveAccount()
        }
    }

    private fun fill() {
        if (selectedAccount!=null){
            binding.apply {
                accountName.setText(selectedAccount.name)
                amount.setText(selectedAccount.amount.toString())
            }
        }
    }

    private fun saveAccount() {


        CoroutineScope(Dispatchers.IO).launch {
            if(selectedAccount==null){
                val account = Account(
                    binding.accountName.text.toString(),
                    AccountType.valueOf(binding.accountType.selectedItem.toString()),
                    binding.currency.selectedItem.toString(),
                    binding.amount.text.toString().toDouble()
                )
                accountDao.insert(account)
            }
            else{
                selectedAccount.name = binding.accountName.text.toString()
                selectedAccount.type = AccountType.valueOf(binding.accountType.selectedItem.toString().uppercase())
                selectedAccount.currency = binding.currency.selectedItem.toString()
                selectedAccount.amount = binding.amount.text.toString().toDouble()
                accountDao.update(selectedAccount)
            }
        }

        // close bottom sheet
        this.dismiss()
        onDismiss.invoke()

    }

    private fun initCurrencySpinners() {
        val currency = resources.getStringArray(com.huseynsharif.common.R.array.currency_list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currency)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.currency.adapter = adapter

        if (selectedAccount!=null){
            binding.currency.setSelection(currency.indexOf(selectedAccount.currency))
        }
    }

    private fun initAccountTypesSpinner() {
        val accountTypes =
            resources.getStringArray(com.huseynsharif.common.R.array.account_type_icons_list)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, accountTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.accountType.adapter = adapter

        if (selectedAccount!=null){
            binding.accountType.setSelection(accountTypes.indexOf(selectedAccount.type.name))
        }

    }

}