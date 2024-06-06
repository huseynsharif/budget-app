package com.huseynsharif.reports.viewModels.accounts

import com.huseynsharif.core.base.BaseViewModel

class AccountsViewModel : BaseViewModel<AccountsState, AccountsEffect, AccountsEvent>() {
    override fun getInitialState() = AccountsState(isLoading = false)
}