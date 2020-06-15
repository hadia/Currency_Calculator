package com.hadia.task.CurrencyCalculator.ui.currencyrates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadia.task.CurrencyCalculator.repository.IMainRepository
import com.hadia.task.CurrencyCalculator.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyRatesListViewModel(private val mainRepository: IMainRepository) : ViewModel() {

    private val currencyList = MutableLiveData<Resource<List<RateData>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchCurrenisesList()
    }

    private fun fetchCurrenisesList() {
        currencyList.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.currencyList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    currencyList.postValue(Resource.success(userList.rates.map { it ->
                        RateData(
                            it.key,
                            it.value
                        )
                    }))
                }, {
                    currencyList.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getCurrencyList(): LiveData<Resource<List<RateData>>> {
        return currencyList
    }

}