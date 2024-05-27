package com.huseynsharif.uikit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huseynsharif.uikit.databinding.FragmentKeyboardBinding

class KeyboardFragment : Fragment() {

    private lateinit var binding: FragmentKeyboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeyboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }



}