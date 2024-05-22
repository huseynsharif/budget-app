package com.huseynsharif.add.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.huseynsharif.add.fragments.ExpensesFragment
import com.huseynsharif.add.fragments.IncomeFragment
import com.huseynsharif.add.fragments.TransferFragment

class SectionPaperAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExpensesFragment()
            1 -> IncomeFragment()
            2 -> TransferFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}