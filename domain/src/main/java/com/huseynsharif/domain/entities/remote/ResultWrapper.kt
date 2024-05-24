package com.huseynsharif.domain.entities.remote

sealed class ResultWrapper {

    data class Success<T>(val value: T) : ResultWrapper()

    data class Error(val exception: Exception) : ResultWrapper()

}

