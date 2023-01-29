package com.example.householdappliances.ui.screen.search

import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentSearchBinding
import com.example.householdappliances.enums.VPControlSearchScreenType
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.screen.search.extension.showIcSearch
import com.example.householdappliances.utils.KeyboardUtils

class SearchFragment : BaseFragment<FragmentSearchBinding>() {


    private lateinit var pagerAdapter: HomePageAdapter
    private lateinit var overviewSearchFragment: OverviewSearchFragment
    private lateinit var resultSearchFragment: ResultSearchFragment

    override fun getContentLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
        initFragmentVPControlSearch()
    }

    override fun initListener() {

        binding.vpControlSearch.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    VPControlSearchScreenType.OVERVIEW.position -> {
                        binding.toolbar.edtSearch.clearFocus()
                        binding.toolbar.viewSetOnclick.visibility = View.VISIBLE
                        showIcSearch()
                        KeyboardUtils.hideKeyboard(activity)
                    }
                    VPControlSearchScreenType.RESULT.position -> {
                        KeyboardUtils.hideKeyboard(activity)
                        binding.toolbar.edtSearch.clearFocus()
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.toolbar.edtSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(
                editText: TextView?,
                actionId: Int,
                p2: KeyEvent?
            ): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (binding.toolbar.edtSearch.text.trim().isNotEmpty()) {
                        resultSearchFragment.getResultByHashtag(binding.toolbar.edtSearch.text.toString())
                        binding.vpControlSearch.setCurrentItem(
                            VPControlSearchScreenType.RESULT.position,
                            false
                        )
                    }
                    return true
                }
                return false
            }
        })

        binding.toolbar.icClearText.setOnClickListener {
            binding.toolbar.edtSearch.setText("")
            KeyboardUtils.hideKeyboard(activity)
            binding.toolbar.edtSearch.clearFocus()
        }
    }

    override fun observerLiveData() {

    }

    private fun initFragmentVPControlSearch() {
        binding.vpControlSearch.isUserInputEnabled = false
        overviewSearchFragment = OverviewSearchFragment.newInstance()
        resultSearchFragment = ResultSearchFragment.newInstance()
        pagerAdapter = HomePageAdapter(parentFragmentManager, lifecycle)
        pagerAdapter.addFragment(overviewSearchFragment)
        pagerAdapter.addFragment(resultSearchFragment)
        binding.vpControlSearch.adapter = pagerAdapter
        binding.vpControlSearch.offscreenPageLimit = pagerAdapter.itemCount
        binding.vpControlSearch.currentItem = VPControlSearchScreenType.OVERVIEW.position
    }
}