package com.hadia.task.CurrencyCalculator.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hadia.task.CurrencyCalculator.ui.viewmodel.MainViewModel
import com.hadia.task.CurrencyCalculator.data.api.ApiHelper
import com.hadia.task.CurrencyCalculator.repository.MainRepository

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(ApiHelper())) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}