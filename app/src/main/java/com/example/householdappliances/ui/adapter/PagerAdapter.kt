package com.example.householdappliances.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptounderground.ui.screen.markets.MyCartFragment
import com.example.householdappliances.ui.screen.account.AccountFragment
import com.example.householdappliances.ui.screen.home.HomeFragment
import com.example.householdappliances.ui.screen.home.viewpager.CategoryFragment
import com.example.householdappliances.ui.screen.home.viewpager.ForYouFragment
import com.example.householdappliances.ui.screen.home.viewpager.TrendFragment
import com.example.householdappliances.ui.screen.search.SearchFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ForYouFragment()
            }
            1 -> {
                SearchFragment()
            }
            2 -> {
                TrendFragment()
            }
            else -> {
                CategoryFragment()
            }
        }
    }
}