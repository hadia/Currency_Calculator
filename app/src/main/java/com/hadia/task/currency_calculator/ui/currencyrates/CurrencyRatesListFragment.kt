package com.hadia.task.currency_calculator.ui.currencyrates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadia.task.currency_calculator.R
import com.hadia.task.currency_calculator.ui.base.ViewModelFactory
import com.hadia.task.currency_calculator.utils.Status
import com.hadia.task.currency_calculator.utils.extension.loadFromUrl
import kotlinx.android.synthetic.main.fragment_currency_rates_list.*


class CurrencyRatesListFragment : Fragment() {
    private val currencyRatesListViewModel by viewModels<CurrencyRatesListViewModel> {
        ViewModelFactory()
    }

    private lateinit var listAdapter: CurrencyRatesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_rates_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        currency_Iv.loadFromUrl("https://www.countryflags.io/EU/flat/16.png")
        recyclerView.layoutManager = LinearLayoutManager(context)
        listAdapter =
            CurrencyRatesListAdapter(
                arrayListOf()
            ) { view, rateData ->
                Navigation.findNavController(view)
                    .navigate(
                        CurrencyRatesListFragmentDirections
                            .actionCurrencyRatesListFragmentToCurrencyCalculator(rateData)
                    )
            }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = listAdapter
    }

    private fun setupObserver() {
        currencyRatesListViewModel.getCurrencyList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data.let { rates -> rates?.let { it1 -> renderList(it1) } }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(rates: List<RateData>) {
        listAdapter.updateData(rates)
        listAdapter.notifyDataSetChanged()
    }
}