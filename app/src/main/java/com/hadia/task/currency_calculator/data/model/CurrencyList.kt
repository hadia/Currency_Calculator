package com.hadia.task.currency_calculator.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyList(

    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
)
