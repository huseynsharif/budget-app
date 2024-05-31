package com.huseynsharif.add.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huseynsharif.add.databinding.FragmentExpensesBinding
import com.huseynsharif.add.viewModels.expenses.ExpensesEffect
import com.huseynsharif.add.viewModels.expenses.ExpensesEvent
import com.huseynsharif.add.viewModels.expenses.ExpensesState
import com.huseynsharif.add.viewModels.expenses.ExpensesViewModel
import com.huseynsharif.common.getResourceIdByName
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.domain.entities.RecordType
import com.huseynsharif.uikit.AccountListBottomSheet

class ExpensesFragment : BaseFragment<FragmentExpensesBinding, ExpensesViewModel, ExpensesState, ExpensesEffect, ExpensesEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExpensesBinding
        = { inflater, viewGroup, value ->
        FragmentExpensesBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = ExpensesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryIcon.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(RecordType.EXPENSES, binding.categoryName.text.toString()){iconName->
                binding.categoryIcon.setImageResource(getResourceIdByName(requireContext(), iconName))
                binding.categoryName.text = iconName
            }
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        binding.accountIcon.setOnClickListener{
            val bottomSheetFragment = AccountListBottomSheet()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }
}