package com.huseynsharif.add.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.add.R
import com.huseynsharif.add.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)


        return binding.root
    }


}