package com.huseynsharif.add.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.add.R
import com.huseynsharif.add.adapter.IconsAdapter
import com.huseynsharif.add.databinding.FragmentBottomSheetBinding
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.RecordType
import javax.inject.Inject

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding;

    private lateinit var adapter:IconsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = IconsAdapter(requireContext())
        val layoutManager = GridLayoutManager(requireContext(), 4)
        binding.categories.layoutManager = layoutManager
        binding.categories.adapter = adapter

        val array = resources.getStringArray(com.huseynsharif.common.R.array.expense_icons_list)
        val icons = mutableListOf<Category>()
        for (a in array){
            icons.add(Category(a, a, RecordType.EXPENSES))
        }

        adapter.submitList(icons)
    }
}