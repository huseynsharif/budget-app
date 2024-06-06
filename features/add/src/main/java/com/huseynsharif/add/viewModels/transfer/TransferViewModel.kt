package com.huseynsharif.add.viewModels.transfer

import androidx.lifecycle.viewModelScope
import com.huseynsharif.add.viewModels.expenses.ExpensesViewModel
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.entities.RecordType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val accountDao: AccountDao
) : BaseViewModel<TransferState, TransferEffect, TransferEvent>() {
    override fun getInitialState() = TransferState(isLoading = false)

    init {
        initEventSubscribers()
    }

    override fun onEventUpdate(event: TransferEvent) {
        super.onEventUpdate(event)

        when(event){
            is TransferEvent.SetSelectedAccountFrom -> setSelectedAccountFrom(event.account)
            is TransferEvent.SetSelectedAccountTo -> setSelectedAccountTo(event.account)
            is TransferEvent.AddTransfer -> saveRecord(event.record)
        }
    }

    private fun setSelectedAccountFrom(account: Account) {
        setState(
            getCurrentState().copy(
                selectedAccountFrom = account
            )
        )
    }

    private fun setSelectedAccountTo(account: Account) {
        setState(
            getCurrentState().copy(
                selectedAccountTo = account
            )
        )
    }

    private fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            record.amountUsd = if (record.account.currency == ExpensesViewModel.USD) {
                record.amount
            } else {
                exchange(record.account.currency, ExpensesViewModel.USD, record.amount)
            }
            record.amountTo = if (record.account.currency == record.accountTo?.currency){
                record.amount
            } else{
                exchange(record.account.currency, record.accountTo?.currency!!, record.amount)
            }
            val accountFrom = record.account
            val accountTo = record.accountTo
            accountFrom.amount -= record.amount
            accountTo?.amount = accountTo?.amount!! + record.amountTo!!
            accountDao.update(accountFrom)
            accountDao.update(accountTo)
            recordDao.insert(record)
        }
    }

    fun getSelectedAccountFrom(): Account? {
        return state.value.selectedAccountFrom
    }

    fun getSelectedAccountTo(): Account? {
        return state.value.selectedAccountTo
    }

    fun getTransferCategory():Category{
        return Category("transfer", "transfer", RecordType.TRANSFER)
    }

}