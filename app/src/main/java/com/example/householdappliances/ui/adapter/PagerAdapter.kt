package com.example.householdappliances.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.householdappliances.ui.screen.home.viewpager.ForYouFragment
import com.example.householdappliances.ui.screen.home.viewpager.TrendFragment

open class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return ForYouFragment()
    }
}