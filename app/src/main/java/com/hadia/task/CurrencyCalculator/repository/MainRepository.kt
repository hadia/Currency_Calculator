package com.hadia.task.CurrencyCalculator.repository

import com.hadia.task.CurrencyCalculator.data.model.CurrencyList
import com.hadia.task.CurrencyCalculator.data.api.ApiHelper
import io.reactivex.Single

open class MainRepository(private val apiHelper: ApiHelper) : IMainRepository {

    override val currencyList: Single<CurrencyList> by lazy {
        apiHelper.getCurrencies()
    }

}