package com.huseynsharif.records.viewModels

import com.huseynsharif.core.base.BaseViewModel

class RecordsViewModel : BaseViewModel<RecordsState, RecordsEffect, RecordsEvent>() {
    override fun getInitialState() = RecordsState(isLoading = false)
}