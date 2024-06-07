package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.uikit.databinding.FragmentAccountInfoBottomSheetBinding

class AccountInfoBottomSheet(
    private val account: Account
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAccountInfoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountInfoBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            name.text = account.name
            type.text = account.type.name
            currency.text = account.currency
            balance.text = account.amount.toString()
        }
    }
}