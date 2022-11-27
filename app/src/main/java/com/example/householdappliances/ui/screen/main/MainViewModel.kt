package com.example.householdappliances.ui.screen.main

import androidx.lifecycle.ViewModel
import com.example.householdappliances.base.SingleLiveEvent
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.network.END_POINT_GET_ALL_CATEGORY
import com.example.householdappliances.repository.CategoryRepository
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.network.END_POINT_GET_ITEM_BY_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val categoriseResult = SingleLiveEvent<Result<List<Category>>>()
    fun getAllCategory(
        url: String? = END_POINT_GET_ALL_CATEGORY
    ) {
        val request = categoryRepository.getAllCategories(
            url = url
        )
        categoriseResult.addSource(request) {
            categoriseResult.postValue(it)

        }
    }

    val itemByCategorise = SingleLiveEvent<Result<List<Item>>>()
    fun getItemByCategory(
        url: String? = END_POINT_GET_ITEM_BY_CATEGORY,
        category: Category
    ) {
        val request = categoryRepository.getItemByCategories(
            url = url,
            category
        )
        itemByCategorise.addSource(request) {
            itemByCategorise.postValue(it)
        }
    }

}