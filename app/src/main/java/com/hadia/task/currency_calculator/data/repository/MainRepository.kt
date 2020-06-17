package com.hadia.task.currency_calculator.data.repository

import com.hadia.task.currency_calculator.data.model.CurrencyList
import com.hadia.task.currency_calculator.data.api.ApiHelper
import io.reactivex.Single

 class MainRepository(private val apiHelper: ApiHelper) : IMainRepository {

    override suspend fun getCurrencyList(): CurrencyList = apiHelper.getCurrencies()
}