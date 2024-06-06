package com.huseynsharif.add.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.huseynsharif.add.databinding.FragmentTransferBinding
import com.huseynsharif.add.viewModels.transfer.TransferEffect
import com.huseynsharif.add.viewModels.transfer.TransferEvent
import com.huseynsharif.add.viewModels.transfer.TransferState
import com.huseynsharif.add.viewModels.transfer.TransferViewModel
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.common.showShortToast
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.entities.RecordType
import com.huseynsharif.uikit.AccountListBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment :
    BaseFragment<FragmentTransferBinding, TransferViewModel, TransferState, TransferEffect, TransferEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding =
        { inflater, viewGroup, value ->
            FragmentTransferBinding.inflate(inflater, viewGroup, value)
        }


    override fun getViewModelClass() = TransferViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            accountFromIcon.setOnClickListener {
                initAccountListBottomSheetFrom()
            }
            accountFromName.setOnClickListener {
                initAccountListBottomSheetFrom()
            }
            fromText.setOnClickListener {
                initAccountListBottomSheetFrom()
            }
            accountToIcon.setOnClickListener {
                initAccountListBottomSheetTo()
            }
            accountToName.setOnClickListener {
                initAccountListBottomSheetTo()
            }
            toText.setOnClickListener {
                initAccountListBottomSheetTo()
            }
            keyboard.onSubmit = {
                if (checkFields()){
                    saveRecord()
                    showShortToast(requireContext(), "Transfer has been saved.")
                }
            }
        }
    }

    private fun initAccountListBottomSheetFrom() {
        val bottomSheetFragment =
            AccountListBottomSheet(binding.accountFromName.text.toString()) { account ->
                binding.accountFromIcon.setImageResource(
                    getResourceIdByName(
                        requireContext(), account.type.name.lowercase()
                    )
                )
                binding.accountFromName.text = account.name
                binding.fromCurrency.apply {
                    text = account.currency
                    visibility = View.VISIBLE
                }
                postEvent(TransferEvent.SetSelectedAccountFrom(account))
            }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun initAccountListBottomSheetTo() {
        val bottomSheetFragment =
            AccountListBottomSheet(binding.accountToName.text.toString()) { account ->
                binding.accountToIcon.setImageResource(
                    getResourceIdByName(
                        requireContext(), account.type.name.lowercase()
                    )
                )
                binding.accountToName.text = account.name
                binding.toCurrency.apply {
                    text = account.currency
                    visibility = View.VISIBLE
                }

                postEvent(TransferEvent.SetSelectedAccountTo(account))
            }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun saveRecord() {
        val record = Record(
            RecordType.TRANSFER,
            viewModel.getSelectedAccountFrom()!!,
            binding.keyboard.getResult(),
            viewModel.getTransferCategory(),
            binding.keyboard.dateMillis,
            binding.keyboard.getBinding().noteEditText.text?.toString(),
            viewModel.getSelectedAccountTo()
        )
        viewModel.postEvent(
            TransferEvent.AddTransfer(record)
        )
    }

    private fun checkFields(): Boolean {
        return if (viewModel.getSelectedAccountFrom()==null) {
            showShortToast(requireContext(), "You have to select an account FROM.")
            false
        } else if (viewModel.getSelectedAccountTo()==null) {
            showShortToast(requireContext(), "You have to select an account TO.")
            false
        }
        else if (viewModel.getSelectedAccountTo()==viewModel.getSelectedAccountFrom()) {
            showShortToast(requireContext(), "Accounts can't be the same.")
            false
        }
        else if(binding.keyboard.getBinding().input.text.isEmpty()){
            Toast.makeText(requireContext(), "You have to enter the amount.", Toast.LENGTH_LONG)
                .show()
            showShortToast(requireContext(), "You have to enter the amount.")
            false
        }
        else {
            true
        }
    }
}