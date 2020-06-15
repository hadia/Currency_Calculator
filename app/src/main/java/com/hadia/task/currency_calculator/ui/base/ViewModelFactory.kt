package com.hadia.task.currency_calculator.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hadia.task.currency_calculator.data.api.ApiHelper
import com.hadia.task.currency_calculator.data.repository.MainRepository
import com.hadia.task.currency_calculator.ui.currencyrates.CurrencyRatesListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CurrencyRatesListViewModel::class.java)) {
            return CurrencyRatesListViewModel(MainRepository(ApiHelper())) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}