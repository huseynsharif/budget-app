package com.huseynsharif.add.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.huseynsharif.add.R
import com.huseynsharif.add.adapter.SectionPaperAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val sectionsPagerAdapter = SectionPaperAdapter(this)
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Expenses"
                1 -> "Income"
                2 -> "Transfer"
                else -> null
            }
        }.attach()
    }

}