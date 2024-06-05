package com.huseynsharif.add.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.huseynsharif.add.databinding.FragmentExpensesBinding
import com.huseynsharif.add.viewModels.expenses.ExpensesEffect
import com.huseynsharif.add.viewModels.expenses.ExpensesEvent
import com.huseynsharif.add.viewModels.expenses.ExpensesState
import com.huseynsharif.add.viewModels.expenses.ExpensesViewModel
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.entities.RecordType
import com.huseynsharif.uikit.AccountListBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpensesFragment :
    BaseFragment<FragmentExpensesBinding, ExpensesViewModel, ExpensesState, ExpensesEffect, ExpensesEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExpensesBinding =
        { inflater, viewGroup, value ->
            FragmentExpensesBinding.inflate(inflater, viewGroup, value)
        }


    override fun getViewModelClass() = ExpensesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            categoryIcon.setOnClickListener {
                initCategoryBottomSheet()
            }
            categoryName.setOnClickListener {
                initCategoryBottomSheet()
            }
            accountIcon.setOnClickListener {
                initAccountListBottomSheet()
            }
            accountName.setOnClickListener {
                initAccountListBottomSheet()
            }
            keyboard.onSubmit = {
                if (checkFields()) {
                    saveRecord()
                    Toast.makeText(requireContext(), "Record has been saved.", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        return if (viewModel.getSelectedAccount()==null) {
            Toast.makeText(requireContext(), "You have to select an account.", Toast.LENGTH_LONG)
                .show()
            false
        }
        else if(binding.keyboard.getBinding().input.text.isEmpty()){
            Toast.makeText(requireContext(), "You have to enter the amount.", Toast.LENGTH_LONG)
                .show()
            false
        }
        else {
            true
        }
    }


    private fun initCategoryBottomSheet() {
        val bottomSheetFragment = BottomSheetFragment(
            RecordType.EXPENSES, binding.categoryName.text.toString()
        ) { iconName ->
            binding.categoryIcon.setImageResource(
                getResourceIdByName(
                    requireContext(), iconName
                )
            )
            binding.categoryName.text = iconName
            viewModel.setSelectedCategory(Category(iconName, iconName, RecordType.EXPENSES))
        }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun initAccountListBottomSheet() {
        val bottomSheetFragment =
            AccountListBottomSheet(binding.accountName.text.toString()) { account ->
                binding.accountIcon.setImageResource(
                    getResourceIdByName(
                        requireContext(), account.type.name.lowercase()
                    )
                )
                binding.accountName.text = account.name
                viewModel.setSelectedAccount(account)
            }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun saveRecord() {
        val record = Record(
            RecordType.EXPENSES,
            viewModel.getSelectedAccount()!!,
            binding.keyboard.getResult(),
            viewModel.getSelectedCategory()!!,
            binding.keyboard.dateMillis,
            binding.keyboard.getBinding().noteEditText.text?.toString()
        )
        viewModel.postEvent(
            ExpensesEvent.AddExpense(record)
        )
    }
}