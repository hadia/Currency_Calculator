package com.hadia.task.currency_calculator.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class CurrencyToCountryCodeConverter() {
    companion object {
        private fun getJsonDataFromCurrencyAsset(context: Context): String? {
            val jsonString: String
            try {
                jsonString =
                    context.assets.open("currency.json").bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getCountryCodeForCurrency(context: Context, currency: String): String? {

            val jsonFileString = getJsonDataFromCurrencyAsset(context)
            Log.i("data", jsonFileString)

            val countryCodeCurrencyMapType = object : TypeToken<HashMap<String, String>>() {}.type

            val countryCodeCurrencyMap: HashMap<String, String> =
                Gson().fromJson(jsonFileString, countryCodeCurrencyMapType)
            return countryCodeCurrencyMap.get(currency)
        }
    }
}