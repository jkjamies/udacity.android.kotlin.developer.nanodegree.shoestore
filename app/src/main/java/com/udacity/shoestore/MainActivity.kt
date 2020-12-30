package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.screens.listings.ShoeViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var shoeViewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        Timber.i("onCreate called")

        // Obtain navController
        val navController = this.findNavController(R.id.myNavHostFragment)
        // AppBarConfiguration sets the top level fragment to be listingsFragment (perspective of AppBar)
        // This will prevent up home back arrow from showing on this fragment
        appBarConfiguration = AppBarConfiguration(setOf(R.id.listingsFragment))
        // Set up the action bar with nav controller using this appBarConfiguration (details will have up home back arrow)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        shoeViewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        Timber.i("onSupportNavigateUp called")
        val navController = this.findNavController(R.id.myNavHostFragment)
        // use navigation ui instead of navigation controller so navigation ui can replace up with drawer button
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
