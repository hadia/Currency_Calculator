package com.hadia.task.currency_calculator.ui.currencyrates

import androidx.lifecycle.*
import com.hadia.task.currency_calculator.data.model.CurrencyList
import com.hadia.task.currency_calculator.data.repository.IMainRepository
import com.hadia.task.currency_calculator.utils.Resource
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class CurrencyRatesListViewModel(private val mainRepository: IMainRepository,private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {


     var currencyList = liveData (dispatcher){
         emit( Resource.loading(null))

         try {
             val  currencyRateList=mainRepository.getCurrencyList()
         emit(Resource.success(currencyRateList.rates.map { rate ->
             RateData(
                 rate.key,
                 rate.value
             )
         }))
         } catch (exception: Exception) {
            emit(Resource.error("Something Went Wrong", null))
         }

     }




}