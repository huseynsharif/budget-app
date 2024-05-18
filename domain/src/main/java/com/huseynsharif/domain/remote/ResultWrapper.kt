package com.huseynsharif.domain.remote

sealed class ResultWrapper {

    data class Success<T>(val value: T) : ResultWrapper()

    data class Error(val exception: Exception) : ResultWrapper()

}

