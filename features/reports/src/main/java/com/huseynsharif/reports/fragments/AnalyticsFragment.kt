package com.huseynsharif.reports.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.reports.R
import com.huseynsharif.reports.databinding.FragmentAnalyticsBinding
import com.huseynsharif.reports.databinding.FragmentReportsBinding
import com.huseynsharif.reports.viewModels.accounts.AccountsEffect
import com.huseynsharif.reports.viewModels.accounts.AccountsEvent
import com.huseynsharif.reports.viewModels.accounts.AccountsState
import com.huseynsharif.reports.viewModels.accounts.AccountsViewModel
import com.huseynsharif.reports.viewModels.analytics.AnalyticsEffect
import com.huseynsharif.reports.viewModels.analytics.AnalyticsEvent
import com.huseynsharif.reports.viewModels.analytics.AnalyticsState
import com.huseynsharif.reports.viewModels.analytics.AnalyticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@AndroidEntryPoint
class AnalyticsFragment : BaseFragment<FragmentAnalyticsBinding, AnalyticsViewModel, AnalyticsState, AnalyticsEffect, AnalyticsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAnalyticsBinding
            = { inflater, viewGroup, value ->
        FragmentAnalyticsBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = AnalyticsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReports()
    }

    private fun showReports() {
        lifecycleScope.launch {
            val decimalFormat = DecimalFormat("#.##")
            viewModel.findSumOfExpenses().combine(viewModel.findSumOfIncome()) { expenses, income ->
                Pair(expenses ?: 0.0, income ?: 0.0)
            }.collect { (expenses, income) ->
                binding.expense.text = decimalFormat.format(expenses)
                binding.income.text = decimalFormat.format(income)
                binding.balance.text = decimalFormat.format(income - expenses)
            }
        }
    }

}