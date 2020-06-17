package com.hadia.task.currency_calculator.data.api

import com.hadia.task.currency_calculator.data.model.CurrencyList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRatesEndpoints {
    @GET("api/latest?")
    fun getCurrencies(@Query("access_key") api_key:String): Single<CurrencyList>
}
