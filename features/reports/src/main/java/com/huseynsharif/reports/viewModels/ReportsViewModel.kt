package com.huseynsharif.reports.viewModels

import com.huseynsharif.core.base.BaseViewModel

class ReportsViewModel : BaseViewModel<ReportsState, ReportsEffect, ReportsEvent>() {
    override fun getInitialState() = ReportsState(isLoading = false)


}