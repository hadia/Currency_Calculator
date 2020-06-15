package com.hadia.task.CurrencyCalculator.ui.currencyrates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadia.task.CurrencyCalculator.R
import com.hadia.task.CurrencyCalculator.ui.base.ViewModelFactory
import com.hadia.task.CurrencyCalculator.utils.Status
import kotlinx.android.synthetic.main.fragment_currency_rates_list.*


class CurrencyRatesListFragment : Fragment() {
    private lateinit var currencyRatesListViewModel: CurrencyRatesListViewModel
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
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        listAdapter =
            CurrencyRatesListAdapter(
                arrayListOf()
            ) { view, rateData ->
                Navigation.findNavController(view)
                    .navigate(CurrencyRatesListFragmentDirections
                        .actionCurrencyRatesListFragmentToCurrencyCalculator(rateData))
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

    private fun setupViewModel() {

        currencyRatesListViewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(CurrencyRatesListViewModel::class.java)
    }
}