package com.hadia.task.currency_calculator.data.repository

import com.hadia.task.currency_calculator.data.model.CurrencyList
import com.hadia.task.currency_calculator.data.api.ApiHelper
import io.reactivex.Single

open class MainRepository(private val apiHelper: ApiHelper) : IMainRepository {

    override val currencyList: Single<CurrencyList> by lazy {
        apiHelper.getCurrencies()
    }

}