package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentHistorySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistorySearchFragment : BaseFragment<FragmentHistorySearchBinding>() {

    private val viewModel: SearchViewModel by activityViewModels()
//    private lateinit var onClickHistory: (SearchHistory) -> Unit
//    private lateinit var historySearchAdapter: HistorySearchAdapter
//    private var searchHistories = ArrayList<SearchHistory>()
    companion object {
        fun newInstance(): HistorySearchFragment {
            return HistorySearchFragment()
        }
    }

//    fun setOnclickHistory(onClickHistory: (SearchHistory) -> Unit) {
//        this.onClickHistory = onClickHistory
//    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_history_search
    }

    override fun initView() {
//        historySearchAdapter =
//            HistorySearchAdapter(searchHistories as MutableList<SearchHistory?>,
//                onClickHistory = {
//                    if (it != null) {
//                        onClickHistory.invoke(it)
//                        eventTrackingManager.sendSearchEvent(
//                            eventName = EVENT_V2_G3_SEARCH_WITH_HISTORY
//                        )
//                    }
//                },
//                onClickRemoveHistory = {
//                    if (it != null) {
//                        searchHistories.remove(it)
//                        viewModel.removeSearchHistory(it)
//                        historySearchAdapter.notifyDataSetChanged()
//                        EventBus.getDefault().post(MessageEvent(MessageEvent.UPDATE_SEARCH_HISTORY))
//                    }
//                })
//        setupLinearLayoutRecyclerView(requireContext(), binding.rvHistorySearch)
//        binding.rvHistorySearch.adapter = historySearchAdapter
    }

    override fun initListener() {}

//    fun getSearchHistory() {
//        searchHistories.clear()
//        searchHistories.addAll(viewModel.getSearchHistory(5))
//        historySearchAdapter.notifyDataSetChanged()
//    }

    override fun observerLiveData() {}
}