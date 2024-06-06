package com.huseynsharif.reports.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.reports.R
import com.huseynsharif.reports.databinding.FragmentAccountBinding
import com.huseynsharif.reports.databinding.FragmentAnalyticsBinding
import com.huseynsharif.reports.viewModels.accounts.AccountsEffect
import com.huseynsharif.reports.viewModels.accounts.AccountsEvent
import com.huseynsharif.reports.viewModels.accounts.AccountsState
import com.huseynsharif.reports.viewModels.accounts.AccountsViewModel


class AccountsFragment : BaseFragment<FragmentAccountBinding, AccountsViewModel, AccountsState, AccountsEffect, AccountsEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding
            = { inflater, viewGroup, value ->
        FragmentAccountBinding.inflate(inflater, viewGroup, value)
    }

    override fun getViewModelClass() = AccountsViewModel::class.java


}