package com.example.householdappliances.ui.screen.home.viewpager

import android.view.View
import androidx.fragment.app.activityViewModels
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.databinding.FragmentForYouBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.CategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupGridLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForYouFragment : BaseFragment<FragmentForYouBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private val listImageSlider: ArrayList<SlideModel> = ArrayList()
    private val listCategory: ArrayList<Category> = ArrayList()

    override fun getContentLayout(): Int {
        return R.layout.fragment_for_you
    }

    override fun initView() {
        listImageSlider.add(SlideModel(R.drawable.slider1, ScaleTypes.CENTER_CROP))
        listImageSlider.add(SlideModel(R.drawable.slider2, ScaleTypes.CENTER_CROP))
        listImageSlider.add(SlideModel(R.drawable.slider3, ScaleTypes.CENTER_CROP))
        listImageSlider.add(SlideModel(R.drawable.slider4, ScaleTypes.CENTER_CROP))
        listImageSlider.add(SlideModel(R.drawable.slider5, ScaleTypes.CENTER_CROP))
        binding.imgSlide.setImageList(listImageSlider, ScaleTypes.CENTER_CROP)
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
            onClickCategoryListener = { _, category ->
                navigationManager.gotoCategoryFragmentScreen(parentFragmentManager, category)
            })
        setupGridLayoutRecyclerView(requireContext(), binding.rvListCategory, 2)
        binding.rvListCategory.adapter = categoryAdapter
    }

    override fun onResume() {
        super.onResume()
        if (listCategory.isEmpty()) viewModel.getAllCategory()
    }
}