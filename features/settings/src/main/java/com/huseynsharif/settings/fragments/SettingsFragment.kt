package com.huseynsharif.settings.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.settings.databinding.FragmentSettingsBinding
import com.huseynsharif.settings.viewModel.SettingsEffect
import com.huseynsharif.settings.viewModel.SettingsEvent
import com.huseynsharif.settings.viewModel.SettingsState
import com.huseynsharif.settings.viewModel.SettingsViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel, SettingsState, SettingsEffect, SettingsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
            = { inflater, viewGroup, value ->
        FragmentSettingsBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = SettingsViewModel::class.java

}