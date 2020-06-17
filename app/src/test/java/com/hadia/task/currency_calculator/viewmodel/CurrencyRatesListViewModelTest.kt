package com.hadia.task.currency_calculator.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.testcoroutinesrule.TestCoroutineRule
import com.hadia.task.currency_calculator.data.model.CurrencyList
import com.hadia.task.currency_calculator.data.repository.IMainRepository
import com.hadia.task.currency_calculator.ui.currencyrates.CurrencyRatesListViewModel
import com.hadia.task.currency_calculator.ui.currencyrates.RateData
import com.hadia.task.currency_calculator.utils.Resource
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import retrofit2.HttpException
import kotlin.Error


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyRatesListViewModelTest {

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    private lateinit var currencyRatesListModel: CurrencyRatesListViewModel
    @Mock
    lateinit var mockDataRepository: IMainRepository
    @Mock
    lateinit var mockLiveDataObserver: Observer<Resource<List<RateData>>>

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given currencyList then update live data`() {
        testDispatcher.runBlockingTest{
            val currencyList = CurrencyList(true,1,"","",HashMap())
           whenever(mockDataRepository.getCurrencyList()).thenReturn(currencyList)
            currencyRatesListModel = CurrencyRatesListViewModel(mockDataRepository,
                Dispatchers.Unconfined).apply {
               this.currencyList.observeForever(mockLiveDataObserver)
            }
            verify(mockLiveDataObserver).onChanged(Resource.loading(null))
            verify(mockLiveDataObserver).onChanged(Resource.success(emptyList()))
            Assert.assertTrue(currencyRatesListModel.currencyList.value?.data.isNullOrEmpty())
            currencyRatesListModel.currencyList.removeObserver(mockLiveDataObserver)


        }
    }
    @Test
    fun `Given currencyList return error then live data will not update`() {
        testDispatcher.runBlockingTest{
            val errorMessage = "Error Message For You"

            whenever(mockDataRepository.getCurrencyList()) doThrow  RuntimeException(errorMessage)
            currencyRatesListModel = CurrencyRatesListViewModel(mockDataRepository,Dispatchers.Unconfined).apply {
               this.currencyList.observeForever(mockLiveDataObserver)
            }
            verify(mockLiveDataObserver).onChanged(Resource.loading(null))
            verify(mockLiveDataObserver).onChanged(Resource.error("Something Went Wrong", null))

          currencyRatesListModel.currencyList.removeObserver(mockLiveDataObserver)
        }
    }

}