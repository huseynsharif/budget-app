package com.huseynsharif.records.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.records.databinding.FragmentRecordsBinding
import com.huseynsharif.records.viewModels.RecordsEffect
import com.huseynsharif.records.viewModels.RecordsEvent
import com.huseynsharif.records.viewModels.RecordsState
import com.huseynsharif.records.viewModels.RecordsViewModel
import com.huseynsharif.uikit.adapter.RecordsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@AndroidEntryPoint
class RecordsFragment :
    BaseFragment<FragmentRecordsBinding, RecordsViewModel, RecordsState, RecordsEffect, RecordsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecordsBinding =
        { inflater, viewGroup, value ->
            FragmentRecordsBinding.inflate(inflater, viewGroup, value)
        }

    private lateinit var adapter: RecordsAdapter

    override fun getViewModelClass() = RecordsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getAllNotes()
        showReports()

    }

    private fun showReports() {
        lifecycleScope.launch {
            val decimalFormat = DecimalFormat("#.##")
            viewModel.findSumOfExpenses().combine(viewModel.findSumOfIncome()) { expenses, income ->
                    Pair(expenses ?: 0.0, income ?: 0.0)
                }.collect { (expenses, income) ->
                    binding.expenses.text = decimalFormat.format(expenses)
                    binding.income.text = decimalFormat.format(income)
                    binding.balance.text = decimalFormat.format(income - expenses)
                }
        }
    }


    private fun initAdapter() {
        adapter = RecordsAdapter(requireContext(), parentFragmentManager)
        binding.savedRecords.adapter = adapter
    }

    private fun getAllNotes() {
        val savedRecords = viewModel.getAllRecords()
        lifecycleScope.launch {
            savedRecords.collect { list ->
                if (list.isNotEmpty()) {
                    adapter.submitList(list.reversed())
                } else {
                    binding.noRecordsIcon.visibility = View.VISIBLE
                    binding.noRecordsText.visibility = View.VISIBLE
                }
            }
        }
    }

}