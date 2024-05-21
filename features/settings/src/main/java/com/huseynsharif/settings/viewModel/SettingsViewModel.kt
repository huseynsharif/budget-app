package com.huseynsharif.settings.viewModel

import com.huseynsharif.core.base.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsState, SettingsEffect, SettingsEvent>() {
    override fun getInitialState() = SettingsState(isLoading = false)
}