package com.example.householdappliances.ui.screen.main

import android.view.View
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityMainBinding
import com.example.householdappliances.enums.NavigationTabType
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.screen.account.AccountFragment
import com.example.householdappliances.ui.screen.cart.MyCartFragment
import com.example.householdappliances.ui.screen.home.HomeFragment
import com.example.householdappliances.ui.screen.search.SearchFragment
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var adapter : HomePageAdapter
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var profileFragment: AccountFragment
    private lateinit var cartFragment: MyCartFragment

    private val onItemSelectedInBottomNavigationListener =
        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    binding.vpMain.currentItem = NavigationTabType.HOME_MENU_NAV.position
                }
                R.id.nav_search -> {
                    binding.vpMain.currentItem = NavigationTabType.SEARCH_MENU_NAV.position
                }
                R.id.nav_cart -> {
                    binding.vpMain.currentItem = NavigationTabType.CART_MENU_NAV.position
                }
                R.id.nav_account -> {
                    binding.vpMain.currentItem = NavigationTabType.ACCOUNT_MENU_NAV.position
                }
            }
            return@OnItemSelectedListener true
        }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        //setMenuBottomNavigation()
        setupAdapter()
    }

    override fun initListener() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    NavigationTabType.HOME_MENU_NAV.position -> {
                        binding.bottomNavigation.selectedItemId = R.id.nav_home
                    }
                    NavigationTabType.SEARCH_MENU_NAV.position -> {
                        binding.bottomNavigation.selectedItemId = R.id.nav_search
                    }
                    NavigationTabType.CART_MENU_NAV.position -> {
                        binding.bottomNavigation.selectedItemId = R.id.nav_cart
                    }
                    NavigationTabType.ACCOUNT_MENU_NAV.position -> {
                        binding.bottomNavigation.selectedItemId = R.id.nav_account
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

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
        setMenuBottomNavigation()
    }

    private fun initFragment(){
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        profileFragment = AccountFragment()
        cartFragment = MyCartFragment()
        binding.bottomNavigation.setOnItemSelectedListener(onItemSelectedInBottomNavigationListener)
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
                R.id.nav_cart -> {
                    binding.vpMain.currentItem = 2
                }
                R.id.nav_account -> {
                    binding.vpMain.currentItem = 3
                }
            }
            true
        }
    }
}