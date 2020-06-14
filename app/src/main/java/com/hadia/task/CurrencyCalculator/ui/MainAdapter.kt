package com.hadia.task.CurrencyCalculator.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.hadia.task.CurrencyCalculator.R
import com.hadia.task.CurrencyCalculator.ui.RateData
import com.hadia.task.CurrencyCalculator.utils.extension.loadFromUrl
import kotlinx.android.synthetic.main.currency_list_item.view.*


class MainAdapter(
    private var rates: List<RateData>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rateData: RateData) = with(itemView) {
            World.init(context)
            currencyNameTV.text = rateData.currency
            currencyRateTV.text = rateData.amount.toString()
            currencyIV.setImageResource(World.getFlagOf(rateData.currency));
            //currencyIV.loadFromUrl(rateData.icon)
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

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(rates[position])

    fun updateData(list: List<RateData>) {
        rates = list
    }

}
