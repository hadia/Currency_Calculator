package com.hadia.task.CurrencyCalculator.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hadia.task.CurrencyCalculator.data.model.CurrencyList
import com.hadia.task.CurrencyCalculator.repository.IMainRepository
import com.hadia.task.CurrencyCalculator.ui.currencyrates.CurrencyRatesListViewModel
import com.hadia.task.CurrencyCalculator.ui.currencyrates.RateData
import com.hadia.task.CurrencyCalculator.utils.Resource
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner::class)
class CurrencyRatesListViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()
    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    private lateinit var currencyRatesListModel: CurrencyRatesListViewModel
    @Mock
    lateinit var mockDataRepository: IMainRepository
    @Mock
    lateinit var mockLiveDataObserver: Observer<Resource<List<RateData>>>
    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)

    }
    @Test
    fun `Given currencyList then update live data`() {
        val currencyList = CurrencyList(true,1,"","",HashMap<String,Double>())

        `when`(mockDataRepository.currencyList)
            .thenReturn(Single.just(currencyList))

        currencyRatesListModel =
            CurrencyRatesListViewModel(
                mockDataRepository
            )
        currencyRatesListModel.getCurrencyList()
        //Check that our live data is updated
        Assert.assertTrue(currencyRatesListModel.getCurrencyList().value?.data.isNullOrEmpty())
    }
    @Test
    fun `Given currencyList return error then live data will not update`() {

        `when`(mockDataRepository.currencyList)
            .thenReturn(Single.error(Throwable()))

        currencyRatesListModel =
            CurrencyRatesListViewModel(
                mockDataRepository
            )
        currencyRatesListModel.getCurrencyList().observeForever(mockLiveDataObserver)
        currencyRatesListModel.getCurrencyList()
        //Check that our live data is updated
        verify(mockLiveDataObserver, times(1)).onChanged(Resource.error("Something Went Wrong", null))
    }

}