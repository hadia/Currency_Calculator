package com.hadia.task.currency_calculator.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyToCountryCodeConverterTest {
    @Test
    fun testGetCountryCodeForCurrency() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            CurrencyToCountryCodeConverter.getCountryCodeForCurrency(
                appContext,
                "AED"
            ), "AE"
        )
        assertEquals(
            CurrencyToCountryCodeConverter.getCountryCodeForCurrency(
                appContext,
                "EGP"
            ), "EG"
        )
        assertNull(
            CurrencyToCountryCodeConverter.getCountryCodeForCurrency(
                appContext,
                "666"
            )
        )
    }
}