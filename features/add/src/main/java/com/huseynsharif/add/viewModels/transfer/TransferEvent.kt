package com.huseynsharif.add.viewModels.transfer

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Record

sealed class TransferEvent{
    data class SetSelectedAccountFrom(val account: Account) : TransferEvent()
    data class SetSelectedAccountTo(val account: Account) : TransferEvent()
    data class AddTransfer(val record: Record) : TransferEvent()
}
