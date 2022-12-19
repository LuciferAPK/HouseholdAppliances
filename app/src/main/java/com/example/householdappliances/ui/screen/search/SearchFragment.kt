package com.example.householdappliances.ui.screen.search

import android.text.Editable
import android.text.TextWatcher
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentSearchBinding
import com.example.householdappliances.enums.VPControlSearchScreenType
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.screen.search.extension.hideIcClearText
import com.example.householdappliances.ui.screen.search.extension.showIcClearText
import org.greenrobot.eventbus.EventBus

class SearchFragment : BaseFragment<FragmentSearchBinding>() {


    private lateinit var pagerAdapter: HomePageAdapter
    private lateinit var overviewSearchFragment: OverviewSearchFragment
    private lateinit var resultSearchFragment: ResultSearchFragment
    private lateinit var historySearchFragment: HistorySearchFragment
    private lateinit var searchingFragment: SearchingFragment

    override fun getContentLayout(): Int {
        return R.layout.fragment_search
    }

//    private val onclickItem: (SearchHistory) -> Unit = {
//        binding.toolbar.edtSearch.setText(it.name)
//        binding.vpControlSearch.setCurrentItem(VPControlSearchScreenType.RESULT.position, false)
//        resultSearchFragment.getResultByHashtag(it.hashtag)
//        viewModel.saveSearchHistory(it)
//        EventBus.getDefault().post(MessageEvent(MessageEvent.UPDATE_SEARCH_HISTORY))
//    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {}

        override fun afterTextChanged(s: Editable) {

            if (binding.toolbar.edtSearch.text?.isEmpty() == true) {
                hideIcClearText()
                binding.vpControlSearch.currentItem = VPControlSearchScreenType.HISTORY.position
            } else {
                binding.vpControlSearch.currentItem = VPControlSearchScreenType.SEARCHING.position
                showIcClearText()
                searchingFragment.search(s.toString())
            }
        }
    }

    override fun initView() {
        initFragmentVPControlSearch()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    private fun initFragmentVPControlSearch() {
        binding.vpControlSearch.isUserInputEnabled = false
        overviewSearchFragment = OverviewSearchFragment.newInstance()
        resultSearchFragment = ResultSearchFragment.newInstance()
        historySearchFragment = HistorySearchFragment.newInstance()
//        historySearchFragment.setOnclickHistory(onclickItem)
        searchingFragment = SearchingFragment.newInstance(onUpdateTextSuggestion = {
            binding.toolbar.edtSearch.setText(it)
            binding.toolbar.edtSearch.requestFocus(1)
//            KeyboardUtils.showSoftKeyboard(binding.toolbar.edtSearch)
        })
//        searchingFragment.setOnClickKeyword(onclickItem)
        pagerAdapter = HomePageAdapter(parentFragmentManager, lifecycle)
        pagerAdapter.addFragment(overviewSearchFragment)
        pagerAdapter.addFragment(historySearchFragment)
        pagerAdapter.addFragment(searchingFragment)
        pagerAdapter.addFragment(resultSearchFragment)
        binding.vpControlSearch.adapter = pagerAdapter
        binding.vpControlSearch.offscreenPageLimit = pagerAdapter.itemCount
        binding.vpControlSearch.currentItem = VPControlSearchScreenType.OVERVIEW.position
    }
}