package com.huseynsharif.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("/exchange")
    suspend fun exchange(@Query("from") from: String, @Query("to") to: String) : Double

}