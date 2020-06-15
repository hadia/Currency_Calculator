package com.hadia.task.CurrencyCalculator.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hadia.task.CurrencyCalculator.data.api.ApiHelper
import com.hadia.task.CurrencyCalculator.repository.MainRepository
import com.hadia.task.CurrencyCalculator.ui.currencyrates.CurrencyRatesListViewModel
import com.hadia.task.CurrencyCalculator.ui.details.CurrencyCalculatorViewModel

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