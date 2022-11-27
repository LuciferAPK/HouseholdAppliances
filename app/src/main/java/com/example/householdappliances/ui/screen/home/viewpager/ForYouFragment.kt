package com.example.householdappliances.ui.screen.home.viewpager

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.databinding.FragmentForYouBinding
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.CategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupGridLayoutRecyclerView
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_for_you.view.*
import javax.inject.Inject

@AndroidEntryPoint
class ForYouFragment : BaseFragment<FragmentForYouBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private val listCategory: ArrayList<Category> = ArrayList()

    override fun getContentLayout(): Int {
        return R.layout.fragment_for_you
    }

    override fun initView() {
        viewModel.getAllCategory()
        setupAdapter()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {
        viewModel.apply {
            categoriseResult.observe(this@ForYouFragment) { result ->
                handleResultWithoutLoading(result, onSuccess = {
                    listCategory.addAll(it)
                    categoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun setupAdapter() {
        categoryAdapter = CategoryAdapter(
            requireContext(),
            listCategory,
            onClickCategoryListener = { position, category ->
                navigationManager.gotoCategoryActivityScreen(category)
            })
        setupGridLayoutRecyclerView(requireContext(), binding.rvListCategory, 2)
        binding.rvListCategory.adapter = categoryAdapter
    }
}