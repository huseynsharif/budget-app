package com.huseynsharif.add.viewModels

import com.huseynsharif.core.base.BaseViewModel

class AddViewModel : BaseViewModel<AddState, AddEffect, AddEvent>() {
    override fun getInitialState() = AddState(isLoading = false)
}