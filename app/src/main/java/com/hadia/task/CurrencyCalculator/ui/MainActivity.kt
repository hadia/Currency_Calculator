package com.hadia.task.CurrencyCalculator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hadia.task.CurrencyCalculator.R

class MainActivity : AppCompatActivity() {

    private lateinit var  mNavigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    override fun onSupportNavigateUp(): Boolean {
        return mNavigationController.navigateUp()
    }

}