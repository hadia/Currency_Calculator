package com.hadia.task.CurrencyCalculator.data.api

import com.hadia.task.CurrencyCalculator.data.model.CurrencyList
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyRatesEndpoints {
    @GET("api/latest?access_key=5f00a787c882b717827b856055b007dd")
    fun getCurrencies(): Single<CurrencyList>
}
