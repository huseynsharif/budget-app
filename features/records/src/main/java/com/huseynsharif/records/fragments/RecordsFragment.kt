package com.huseynsharif.records.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.records.databinding.FragmentRecordsBinding
import com.huseynsharif.records.viewModels.RecordsEffect
import com.huseynsharif.records.viewModels.RecordsEvent
import com.huseynsharif.records.viewModels.RecordsState
import com.huseynsharif.records.viewModels.RecordsViewModel


class RecordsFragment : BaseFragment<FragmentRecordsBinding, RecordsViewModel, RecordsState, RecordsEffect, RecordsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecordsBinding
        = { inflater, viewGroup, value ->
        FragmentRecordsBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = RecordsViewModel::class.java



}