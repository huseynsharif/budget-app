package com.huseynsharif.add.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.huseynsharif.add.adapter.SectionPaperAdapter
import com.huseynsharif.add.databinding.FragmentAddBinding
import com.huseynsharif.add.viewModels.add.AddEffect
import com.huseynsharif.add.viewModels.add.AddEvent
import com.huseynsharif.add.viewModels.add.AddState
import com.huseynsharif.add.viewModels.add.AddViewModel
import com.huseynsharif.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment :
    BaseFragment<FragmentAddBinding, AddViewModel, AddState, AddEffect, AddEvent>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding =
        { inflater, viewGroup, value ->
            FragmentAddBinding.inflate(inflater, viewGroup, value)
        }

    override fun getViewModelClass() = AddViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()

    }

    private fun initTabLayout() {
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