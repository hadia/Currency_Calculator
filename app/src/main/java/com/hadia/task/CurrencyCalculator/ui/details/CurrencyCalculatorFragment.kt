package com.hadia.task.CurrencyCalculator.ui.details

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hadia.task.CurrencyCalculator.R
import com.hadia.task.CurrencyCalculator.ui.currencyrates.RateData
import kotlinx.android.synthetic.main.currency_calculator_fragment.*

class CurrencyCalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyCalculatorFragment()
    }

    private lateinit var viewModel: CurrencyCalculatorViewModel
    private lateinit var rateData: RateData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrencyCalculatorViewModel::class.java)
        rateData = CurrencyCalculatorFragmentArgs
            .fromBundle(requireArguments()).rateData
        
        selected_currency_tv.text =rateData.currency
        selected_currency_rate_tv.text ="%.4f".format(rateData.amount)
        base_currency_rate_et.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(editable: Editable) {
                updatedCalculateRate( editable.toString().toDoubleOrNull())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(editable: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updatedCalculateRate( editable.toString().toDoubleOrNull())

            }
        })

    }
    private fun updatedCalculateRate(changedValue:Double?){
        changedValue?.let {changedValue->
            selected_currency_rate_tv.text= (changedValue*rateData.amount).toString()
        }
    }

}