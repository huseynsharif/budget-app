package com.huseynsharif.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseynsharif.domain.entities.remote.ResultWrapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0

abstract class BaseViewModel<State, Effect, Event> : ViewModel(){

    private val viewState: State by lazy { getInitialState() }

    abstract fun getInitialState() :State

    private val _state = MutableStateFlow(viewState)
    val state: StateFlow<State> = _state

    private val _event = MutableSharedFlow<Event>()
    private val event: SharedFlow<Event> = _event

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    protected fun setState(state: State) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    fun postEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    fun postEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    fun initEventSubscribers() {
        _event
            .onEach {
                onEventUpdate(it)
            }.launchIn(viewModelScope)
    }

    protected open fun onEventUpdate(event: Event) {}

    fun getCurrentState() = _state.value

    suspend fun <T> invoke(
        kSuspendFunction0: KSuspendFunction0<T>,
        onError: ((e: Exception) -> Unit)?= null,
        onSuccess: (T) -> Unit
    ) {

        when (val result = invokeRequest(kSuspendFunction0)) {
            is ResultWrapper.Error -> {
                onError?.invoke(result.exception)
                onErrorHandled(result.exception)
            }
            is ResultWrapper.Success<*> -> {

                onSuccess(result.value as T)
            }
        }
    }

    private fun onErrorHandled(exception: Exception) {

    }

    private suspend fun <T> invokeRequest(kSuspendFunction0: KSuspendFunction0<T>): ResultWrapper {
        return try {
            ResultWrapper.Success(kSuspendFunction0.invoke())
        } catch (e: Exception) {
            ResultWrapper.Error(e)
        }
    }
}