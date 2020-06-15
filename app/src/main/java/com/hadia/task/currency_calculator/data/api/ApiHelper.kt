package com.hadia.task.currency_calculator.data.api

class ApiHelper {
    private val ratesApi by lazy {
        ServiceBuilder.buildService(
            CurrencyRatesEndpoints::class.java
        )
    }

    fun getCurrencies() = ratesApi.getCurrencies()
}