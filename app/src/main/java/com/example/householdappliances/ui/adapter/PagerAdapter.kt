package com.example.householdappliances.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.householdappliances.ui.screen.home.viewpager.ForYouFragment
import com.example.householdappliances.ui.screen.home.viewpager.TrendFragment

open class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ForYouFragment()
            }
            else -> {
                TrendFragment()
            }
        }
    }
}