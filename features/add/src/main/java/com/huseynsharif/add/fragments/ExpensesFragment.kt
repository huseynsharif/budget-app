package com.huseynsharif.add.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huseynsharif.add.databinding.FragmentExpensesBinding
import com.huseynsharif.add.viewModels.expenses.ExpensesEffect
import com.huseynsharif.add.viewModels.expenses.ExpensesEvent
import com.huseynsharif.add.viewModels.expenses.ExpensesState
import com.huseynsharif.add.viewModels.expenses.ExpensesViewModel
import com.huseynsharif.core.base.BaseFragment

class ExpensesFragment : BaseFragment<FragmentExpensesBinding, ExpensesViewModel, ExpensesState, ExpensesEffect, ExpensesEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExpensesBinding
        = { inflater, viewGroup, value ->
        FragmentExpensesBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = ExpensesViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomSheetFragment = BottomSheetFragment()
        binding = FragmentExpensesBinding.inflate(layoutInflater)
//        binding.button.setOnClickListener{
            Log.e("TAG", "GAF")
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
//        }
    }



}