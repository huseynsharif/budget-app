package com.huseynsharif.reports.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.huseynsharif.reports.fragments.AccountFragment
import com.huseynsharif.reports.fragments.AnalyticsFragment

class SectionPaperAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AnalyticsFragment()
            1 -> AccountFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}