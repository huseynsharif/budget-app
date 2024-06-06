package com.huseynsharif.reports.viewModels.reports

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    currencyService: CurrencyService
) : BaseViewModel<ReportsState, ReportsEffect, ReportsEvent>(currencyService) {
    override fun getInitialState() = ReportsState(isLoading = false)

}