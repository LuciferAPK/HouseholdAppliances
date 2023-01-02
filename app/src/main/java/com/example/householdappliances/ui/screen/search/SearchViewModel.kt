package com.example.householdappliances.ui.screen.search

import androidx.lifecycle.ViewModel
import com.example.householdappliances.base.Result
import com.example.householdappliances.base.SingleLiveEvent
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.network.END_POINT_SEARCH_ITEM_BY_KEY
import com.example.householdappliances.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    val searchItemResult = SingleLiveEvent<Result<List<Item>>>()
    fun searchItemByKey (
        key: String,
        url: String? = END_POINT_SEARCH_ITEM_BY_KEY
    ) {
        val request = searchRepository.searchItemByKey(url = url, key = key)
        searchItemResult.addSource(request) {
            searchItemResult.postValue(it)
        }
    }
}