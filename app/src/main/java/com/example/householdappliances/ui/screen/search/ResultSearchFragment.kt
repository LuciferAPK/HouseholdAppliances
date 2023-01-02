package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentResultSearchBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class ResultSearchFragment : BaseFragment<FragmentResultSearchBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private val listItem: ArrayList<Item> = ArrayList()
    companion object {
        fun newInstance(): ResultSearchFragment {
            return ResultSearchFragment()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getResultByHashtag(hashtag: String?) {
        if (hashtag != null) {
            viewModel.searchItemByKey(hashtag)
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_result_search
    }

    override fun initView() {
        setupAdapter()
    }

    override fun initListener() {

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun observerLiveData() {
        viewModel.apply {
            searchItemResult.observe(this@ResultSearchFragment) { result ->
                handleResult(result, onSuccess = {
                    listItem.clear()
                    listItem.addAll(it)
                    detailCategoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    private fun setupAdapter() {
        detailCategoryAdapter = DetailCategoryAdapter(
            requireContext(),
            listItem,
            onClickItemCategoryListener = { position, item ->
                navigationManager.gotoDetailActivityScreen()
            },
            onClickAddToCartListener = {

            })
        setupLinearLayoutRecyclerView(context, binding.rvItemResultSearch)
        binding.rvItemResultSearch.adapter = detailCategoryAdapter
    }
}