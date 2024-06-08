package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.uikit.databinding.FragmentAccountInfoBottomSheetBinding
import com.huseynsharif.uikit.databinding.FragmentRecordInfoBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordInfoBottomSheet(
    private val record: Record
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentRecordInfoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordInfoBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fill()

    }

    private fun fill() {
        binding.apply {
            category.text = record.category.title
            type.text = record.category.type.name.lowercase()
            account.text = record.account.name
            currency.text = record.account.currency
            amount.text = record.amount.toString()
            note.text = record.note
            accountTo.text = record.accountTo?.name
            amountTo.text = record.amountTo?.toString()
        }
    }

}