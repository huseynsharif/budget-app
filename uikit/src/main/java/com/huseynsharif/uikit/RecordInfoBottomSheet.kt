package com.huseynsharif.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huseynsharif.data.useCases.DeleteRecordUseCase
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.uikit.databinding.FragmentRecordInfoBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecordInfoBottomSheet(
    private val record: Record
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentRecordInfoBottomSheetBinding

    @Inject
    lateinit var deleteRecordUseCase: DeleteRecordUseCase

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

        binding.btnDelete.setOnClickListener {
            deleteRecord()
        }

    }

    private fun deleteRecord() {
        lifecycleScope.launch(Dispatchers.IO){
            deleteRecordUseCase(record)
        }
        this.dismiss()
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

            if (record.accountTo==null){
                accountToText.visibility = View.GONE
                amountToText.visibility = View.GONE
            }

        }
    }

}