package com.example.householdappliances.ui.screen.main

import android.view.View
import androidx.navigation.findNavController
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setMenuBottomNavigation()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    private fun setMenuBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    navController.navigateUp()
                    navController.navigate(R.id.homeFragment)
                }
                R.id.nav_search -> {
                    navController.navigateUp()
                    navController.navigate(R.id.searchFragment)
                }
                R.id.nav_trades -> {
                    navController.navigateUp()
                    navController.navigate(R.id.myCartFragment)
                }
                R.id.nav_wallets -> {
                    navController.navigateUp()
                    navController.navigate(R.id.accountFragment)
                }
            }
            true
        }
    }
}