package com.hadia.task.currency_calculator.data.api

import com.hadia.task.currency_calculator.data.model.CurrencyList
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyRatesEndpoints {
    @GET("api/latest?access_key=5f00a787c882b717827b856055b007dd")
    suspend fun getCurrencies(): CurrencyList
}
