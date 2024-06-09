package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.common.showShortToast
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.useCases.DeleteAccountUseCase
import com.huseynsharif.data.useCases.DeleteRecordUseCase
import com.huseynsharif.data.useCases.UpdateAccountUseCase
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.uikit.databinding.FragmentAccountInfoBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AccountInfoBottomSheet(
    private val account: Account
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAccountInfoBottomSheetBinding

    @Inject
    lateinit var updateAccountUseCase: UpdateAccountUseCase

    @Inject
    lateinit var deleteAccountUseCase: DeleteAccountUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountInfoBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            name.text = account.name
            type.text = account.type.name
            currency.text = account.currency
            balance.text = account.amount.toString()
            btnDelete.setOnClickListener {
                deleteAccount()
            }
            btnEdit.setOnClickListener {
                showBottomSheet()
            }
        }
    }

    private fun showBottomSheet() {
        val bottomSheet = AddAccountBottomSheet(account)
        bottomSheet.onDismiss = {
            this.dismiss()
        }
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }

    private fun deleteAccount() {
        lifecycleScope.launch(Dispatchers.IO) {
            deleteAccountUseCase(account)
        }
        showShortToast(requireContext(), "You deleted ${account.name}")
        this.dismiss()
    }
}