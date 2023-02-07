package com.example.householdappliances.ui.screen.home

import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.adapter.PagerAdapter
import com.example.householdappliances.ui.screen.account.AccountFragment
import com.example.householdappliances.ui.screen.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        binding.viewPaper.adapter = PagerAdapter(requireActivity())
        setUpViewPager()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

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