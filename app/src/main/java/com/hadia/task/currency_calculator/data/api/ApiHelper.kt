package com.hadia.task.currency_calculator.data.api

import android.os.Build
import com.hadia.task.currency_calculator.BuildConfig

class ApiHelper {
    private val ratesApi by lazy {
        ServiceBuilder.buildService(
            CurrencyRatesEndpoints::class.java
        )
    }

     suspend fun getCurrencies() = ratesApi.getCurrencies(BuildConfig.API_KEY)
}