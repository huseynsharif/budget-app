package com.huseynsharif.add.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import com.huseynsharif.add.databinding.FragmentTransferBinding
import com.huseynsharif.add.viewModels.transfer.TransferEffect
import com.huseynsharif.add.viewModels.transfer.TransferEvent
import com.huseynsharif.add.viewModels.transfer.TransferState
import com.huseynsharif.add.viewModels.transfer.TransferViewModel
import com.huseynsharif.core.base.BaseFragment


class TransferFragment :
    BaseFragment<FragmentTransferBinding, TransferViewModel, TransferState, TransferEffect, TransferEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding =
        { inflater, viewGroup, value ->
            FragmentTransferBinding.inflate(inflater, viewGroup, value)
        }


    override fun getViewModelClass() = TransferViewModel::class.java





}