package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentResultSearchBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ResultSearchFragment : BaseFragment<FragmentResultSearchBinding>() {

    companion object {
        fun newInstance(): ResultSearchFragment {
            return ResultSearchFragment()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getResultByHashtag(hashtag: String?) {

    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_result_search
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun observerLiveData() {

    }
}