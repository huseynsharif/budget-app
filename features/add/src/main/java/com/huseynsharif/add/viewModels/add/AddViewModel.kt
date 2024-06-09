package com.huseynsharif.add.viewModels.add

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class AddViewModel : BaseViewModel<AddState, AddEffect, AddEvent>() {
    override fun getInitialState() = AddState(isLoading = false)
}