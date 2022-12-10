package com.example.householdappliances.ui.screen.login

import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentCreateAccountBinding
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.ui.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_create_account
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.tvCancle.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }
}