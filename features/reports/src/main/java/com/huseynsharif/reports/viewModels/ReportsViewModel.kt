package com.huseynsharif.reports.viewModels

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService


class ReportsViewModel(
    currencyService: CurrencyService
) : BaseViewModel<ReportsState, ReportsEffect, ReportsEvent>(currencyService) {
    override fun getInitialState() = ReportsState(isLoading = false)
}