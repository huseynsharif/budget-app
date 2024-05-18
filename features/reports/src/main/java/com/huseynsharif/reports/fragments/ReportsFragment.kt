package com.huseynsharif.reports.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.reports.databinding.FragmentReportsBinding
import com.huseynsharif.reports.viewModels.ReportsEffect
import com.huseynsharif.reports.viewModels.ReportsEvent
import com.huseynsharif.reports.viewModels.ReportsState
import com.huseynsharif.reports.viewModels.ReportsViewModel


class ReportsFragment : BaseFragment<FragmentReportsBinding, ReportsViewModel, ReportsState, ReportsEffect, ReportsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReportsBinding
            = { inflater, viewGroup, value ->
        FragmentReportsBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = ReportsViewModel::class.java

}