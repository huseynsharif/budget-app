package com.huseynsharif.reports.viewModels.analytics

import com.huseynsharif.core.base.BaseViewModel

class AnalyticsViewModel : BaseViewModel<AnalyticsState, AnalyticsEffect, AnalyticsEvent>() {
    override fun getInitialState() = AnalyticsState(isLoading = false)
}