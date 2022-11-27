package com.example.householdappliances.ui.screen.search

import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentCartBinding
import com.example.householdappliances.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getContentLayout(): Int {
        return R.layout.fragment_search
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