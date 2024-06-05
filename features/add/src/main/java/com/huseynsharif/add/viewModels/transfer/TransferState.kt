package com.huseynsharif.add.viewModels.transfer

import com.huseynsharif.domain.entities.Account

data class TransferState(
    var isLoading:Boolean?=false,
    var selectedAccountFrom:Account?=null,
    var selectedAccountTo:Account?=null
)
