package com.example.householdappliances.ui.screen.main

import android.view.View
import androidx.navigation.findNavController
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityMainBinding
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.screen.account.AccountFragment
import com.example.householdappliances.ui.screen.cart.MyCartFragment
import com.example.householdappliances.ui.screen.home.HomeFragment
import com.example.householdappliances.ui.screen.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var adapter : HomePageAdapter
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var profileFragment: AccountFragment
    private lateinit var cartFragment: MyCartFragment


    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        //setMenuBottomNavigation()
        setupAdapter()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    private fun setupAdapter(){
        adapter = HomePageAdapter(supportFragmentManager, lifecycle)
        initFragment()
        adapter.apply {
            addFragment(homeFragment)
            addFragment(searchFragment)
            addFragment(cartFragment)
            addFragment(profileFragment)
        }
        binding.vpMain.adapter = adapter
    }

    private fun initFragment(){
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        profileFragment = AccountFragment()
        cartFragment = MyCartFragment()
    }

    private fun setMenuBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    binding.vpMain.currentItem = 0
                }
                R.id.nav_search -> {
                    binding.vpMain.currentItem = 1
                }
                R.id.nav_trades -> {
                    binding.vpMain.currentItem = 2
                }
                R.id.nav_wallets -> {
                    binding.vpMain.currentItem = 3
                }
            }
            true
        }
    }
}