package com.hadia.task.currency_calculator.ui.currencyrates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hadia.task.currency_calculator.R
import com.hadia.task.currency_calculator.utils.CurrencyToCountryCodeConverter
import com.hadia.task.currency_calculator.utils.extension.loadFromUrl
import kotlinx.android.synthetic.main.currency_list_item.view.*


class CurrencyRatesListAdapter(
    private var rates: List<RateData>,
    private val listener: (View,RateData) -> Unit
) : RecyclerView.Adapter<CurrencyRatesListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rateData: RateData) = with(itemView) {
            currencyNameTV.text = rateData.currency
            currencyRateTV.text = "%.4f".format(rateData.amount)
            CurrencyToCountryCodeConverter.getCountryCodeForCurrency(context, rateData.currency)
                ?.let {
                    currencyIV.loadFromUrl("https://www.countryflags.io/${it}/flat/64.png")
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.currency_list_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = rates[position]
        holder.itemView.setOnClickListener { listener(holder.itemView,item) }
        holder.bind(item)
    }

    fun updateData(list: List<RateData>) {
        rates = list
    }

}
