package com.huseynsharif.reports.viewModels.reports

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ReportsViewModel : BaseViewModel<ReportsState, ReportsEffect, ReportsEvent>() {
    override fun getInitialState() = ReportsState(isLoading = false)

}