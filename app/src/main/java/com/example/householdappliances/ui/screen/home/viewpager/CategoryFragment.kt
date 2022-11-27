package com.example.householdappliances.ui.screen.home.viewpager

import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentHomeBinding

class CategoryFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_category
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