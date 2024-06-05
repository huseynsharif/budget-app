package com.huseynsharif.records.fragments

import android.opengl.Visibility
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.findSumOfExpenses().collect {
                binding.expenses.text = it.toString()
            }
            viewModel.findSumOfIncome().collect {
                binding.income.text = it.toString()
            }
        }


    }

    private fun initAdapter() {
        adapter = RecordsAdapter(requireContext())
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