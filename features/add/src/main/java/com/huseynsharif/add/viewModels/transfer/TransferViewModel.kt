package com.huseynsharif.add.viewModels.transfer

import com.huseynsharif.core.base.BaseViewModel

class TransferViewModel : BaseViewModel<TransferState, TransferEffect, TransferEvent>() {
    override fun getInitialState() = TransferState(isLoading = false)
}