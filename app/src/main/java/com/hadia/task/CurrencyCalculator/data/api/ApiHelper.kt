package com.hadia.task.CurrencyCalculator.data.api

import com.hadia.task.CurrencyCalculator.data.api.CurrencyRatesEndpoints
import com.hadia.task.CurrencyCalculator.data.api.ServiceBuilder

class ApiHelper {
    private val ratesApi by lazy {
        ServiceBuilder.buildService(
            CurrencyRatesEndpoints::class.java
        )
    }

    fun getCurrencies() = ratesApi.getCurrencies()
}