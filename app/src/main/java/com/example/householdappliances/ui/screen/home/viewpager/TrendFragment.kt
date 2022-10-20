package com.example.householdappliances.ui.screen.home.viewpager

import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.ui.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TrendFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_trending
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }
}