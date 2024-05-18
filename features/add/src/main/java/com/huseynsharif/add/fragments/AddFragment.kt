package com.huseynsharif.add.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huseynsharif.add.R
import com.huseynsharif.add.databinding.FragmentAddBinding
import com.huseynsharif.add.viewModels.AddEffect
import com.huseynsharif.add.viewModels.AddEvent
import com.huseynsharif.add.viewModels.AddState
import com.huseynsharif.add.viewModels.AddViewModel
import com.huseynsharif.core.base.BaseFragment

class AddFragment : BaseFragment<FragmentAddBinding, AddViewModel, AddState, AddEffect, AddEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
            = { inflater, viewGroup, value ->
        FragmentAddBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = AddViewModel::class.java


}