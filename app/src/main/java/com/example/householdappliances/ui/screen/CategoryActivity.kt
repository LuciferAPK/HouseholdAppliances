package com.example.householdappliances.ui.screen

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.ActivityCategoryBinding
import com.example.householdappliances.navigation.KeyDataIntent.CATEGORY
import com.example.householdappliances.navigation.KeyDataIntent.LIST_CATEGORY
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CategoryActivity : BaseActivity<ActivityCategoryBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private val listItemByCategory: ArrayList<Item> = ArrayList()
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private var categoryName: String = ""
    private var categoryID: Int = 0
    override fun getContentLayout(): Int {
        return R.layout.activity_category
    }

    override fun initView() {
        getDataFromBundle()
        viewModel.getItemByCategory(idCategory = categoryID)
        setupAdapter()
        setUpToolbar()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {
        viewModel.apply {
            itemByCategorise.observe(this@CategoryActivity) { result ->
                handleResultWithoutLoading(result, onSuccess = {
                    listItemByCategory.addAll(it)
                    detailCategoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun getDataFromBundle() {
        categoryName = intent.getStringExtra(CATEGORY).toString()
        categoryID = intent.getSerializableExtra(LIST_CATEGORY) as Int
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = categoryName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAdapter() {
        detailCategoryAdapter = DetailCategoryAdapter(
            this,
            listItemByCategory,
            onClickItemCategoryListener = { position, item ->
                Toast.makeText(this, "hihihi", Toast.LENGTH_SHORT).show()
            })
        setupLinearLayoutRecyclerView(this, binding.rvItemCategory)
        binding.rvItemCategory.adapter = detailCategoryAdapter
    }

}