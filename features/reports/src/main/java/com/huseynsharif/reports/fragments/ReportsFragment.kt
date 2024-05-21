package com.huseynsharif.reports.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.huseynsharif.core.base.BaseFragment
import com.huseynsharif.reports.adapters.SectionPaperAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val sectionsPagerAdapter = SectionPaperAdapter(this)
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Analytics"
                1 -> "Accounts"
                else -> null
            }
        }.attach()
    }

}