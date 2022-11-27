package com.example.householdappliances.ui.screen.home

import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.ui.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        binding.viewPaper.adapter = PagerAdapter(this)
        setUpViewPager()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun setUpViewPager() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPaper
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "Dành cho bạn"
                1 -> tab.text = "Trending"
            }
        }.attach()
    }
}