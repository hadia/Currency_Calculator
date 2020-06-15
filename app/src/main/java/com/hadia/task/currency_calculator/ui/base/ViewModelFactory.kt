package com.hadia.task.currency_calculator.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hadia.task.currency_calculator.data.api.ApiHelper
import com.hadia.task.currency_calculator.data.repository.MainRepository
import com.hadia.task.currency_calculator.ui.currencyrates.CurrencyRatesListViewModel
import com.hadia.task.currency_calculator.ui.details.CurrencyCalculatorViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CurrencyRatesListViewModel::class.java)) {
            return CurrencyRatesListViewModel(
                MainRepository(ApiHelper())
            ) as T
        }else if (modelClass.isAssignableFrom(CurrencyCalculatorViewModel::class.java)){
            return CurrencyCalculatorViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}