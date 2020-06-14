package com.hadia.task.CurrencyCalculator.repository

import com.hadia.task.CurrencyCalculator.data.model.CurrencyList
import io.reactivex.Single

interface IMainRepository {
    val currencyList: Single<CurrencyList>
}