package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.databinding.FragmentOverviewSearchBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.CategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupGridLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OverviewSearchFragment : BaseFragment<FragmentOverviewSearchBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private val listCategory: ArrayList<Category> = ArrayList()

    companion object {
        fun newInstance(): OverviewSearchFragment {
            return OverviewSearchFragment()
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_overview_search
    }

    override fun initView() {
        setupAdapter()
        viewModel.getAllCategory()
    }

    private fun setupAdapter() {
        categoryAdapter = CategoryAdapter(
            requireContext(),
            listCategory,
            onClickCategoryListener = { _, category ->
                navigationManager.gotoCategoryFragmentScreen(parentFragmentManager, category)
            })
        setupGridLayoutRecyclerView(requireContext(), binding.rvSearchOverview, 2)
        binding.rvSearchOverview.adapter = categoryAdapter
    }

    override fun initListener() {}

    @SuppressLint("NotifyDataSetChanged")
    override fun observerLiveData() {
        viewModel.apply {
            categoriseResult.observe(this@OverviewSearchFragment) { result ->
                handleResult(result, onSuccess = {
                    listCategory.addAll(it)
                    categoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }
}