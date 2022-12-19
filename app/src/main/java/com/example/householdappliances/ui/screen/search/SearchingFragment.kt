package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentSearchingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingFragment : BaseFragment<FragmentSearchingBinding>(), SearchInterface {

    private lateinit var onUpdateTextSuggestion: (String?) -> Unit

    private val viewModel: SearchViewModel by activityViewModels()
//    private lateinit var onClickKeyword: (SearchHistory) -> Unit
//    private var searchingAdapter: SearchingAdapter? = null
//    private val keywords = ArrayList<Keyword>()
    private var keywordSearch = ""
    private var newStartTimeSearch: Long = 0
    private var oldStartTimeSearch: Long = 0
    private val delaySectionSearch: Long = 1000
    private var handlerSearch = Handler(Looper.getMainLooper())

    @SuppressLint("NotifyDataSetChanged")
    private val runnableSearch = Runnable {
        if (newStartTimeSearch - oldStartTimeSearch >= delaySectionSearch) {
//            keywords.clear()
//            searchingAdapter?.notifyDataSetChanged()
//            checkConnectInternet(
//                callbackIfConnected = {
//                    viewModel.searchKeyword(keywordSearch, 0, 100)
//                },
//                callbackNoInternet = {
//                }
//            )
        }
        oldStartTimeSearch = newStartTimeSearch
    }

    companion object {
        fun newInstance(onUpdateTextSuggestion: (String?) -> Unit): SearchingFragment {
            return SearchingFragment().apply {
                this.onUpdateTextSuggestion = onUpdateTextSuggestion
            }
        }
    }

//    fun setOnClickKeyword(onClickKeyword: (SearchHistory) -> Unit) {
//        this.onClickKeyword = onClickKeyword
//    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_searching
    }

    override fun initView() {
        oldStartTimeSearch = System.currentTimeMillis()
//        searchingAdapter = SearchingAdapter(keywords as MutableList<Keyword?>,
//            onClickKeyword = {
//                if (it != null) {
//                    onClickKeyword.invoke(it.toObjectSearchHistory())
//                    eventTrackingManager.sendSearchEvent(eventName = EVENT_V2_G3_SEARCH_WITH_HASHTAG)
//                }
//            },
//            onClickFillSuggestion = {
//                if (it != null) {
//                    if (this::onUpdateTextSuggestion.isInitialized)
//                        onUpdateTextSuggestion.invoke(it.name)
//                }
//            })
//        setupLinearLayoutRecyclerView(requireContext(), binding.rvSearching)
//        binding.rvSearching.adapter = searchingAdapter
    }

    override fun initListener() {}

    @SuppressLint("NotifyDataSetChanged")
    override fun observerLiveData() {
        viewModel.apply {
//            keywordsResult.observe(this@SearchingFragment) {
//                handleResultWithoutLoading(it, onSuccess = { keywordsResponse ->
//                    if (keywordsResponse.data.isNotEmpty()) {
//                        binding.viewNoData.viewParent.visibility = View.GONE
//                        keywords.addAll(keywordsResponse.data)
//                        searchingAdapter?.notifyDataSetChanged()
//                    }
//                }, onFailure = {})
//            }
        }
    }

    override fun search(keyWord: String) {
//        if (keyWord.length >= 3) {
//            keywordSearch = keyWord
//            handlerSearch.removeCallbacks(runnableSearch)
//            handlerSearch = Handler(Looper.getMainLooper())
//            newStartTimeSearch = System.currentTimeMillis()
//            handlerSearch.postDelayed(runnableSearch, delaySectionSearch)
//        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun clearSearching() {
//        if (keywords.isNotEmpty()) {
//            keywords.clear()
//            searchingAdapter?.notifyDataSetChanged()
//        }
//    }
}