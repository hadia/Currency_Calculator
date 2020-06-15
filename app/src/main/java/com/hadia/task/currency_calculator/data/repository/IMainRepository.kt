package com.hadia.task.currency_calculator.data.repository

import com.hadia.task.currency_calculator.data.model.CurrencyList
import io.reactivex.Single

interface IMainRepository {
    val currencyList: Single<CurrencyList>
}