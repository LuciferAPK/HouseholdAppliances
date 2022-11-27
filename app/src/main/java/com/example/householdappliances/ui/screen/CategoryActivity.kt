package com.example.householdappliances.ui.screen

import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.ActivityCategoryBinding
import com.example.householdappliances.navigation.KeyDataIntent.CATEGORY
import com.example.householdappliances.navigation.KeyDataIntent.LIST_CATEGORY
import com.example.householdappliances.ui.adapter.CategoryAdapter
import com.example.householdappliances.ui.adapter.ItemCategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupGridLayoutRecyclerView
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

@AndroidEntryPoint
class CategoryActivity : BaseActivity<ActivityCategoryBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private val listItemByCategory: ArrayList<Item> = ArrayList()
    private lateinit var itemCategoryAdapter: ItemCategoryAdapter
    private var getNameCategoryFromBundle: String = ""
    private var getListCategoryFromBundle: Category? = null
    override fun getContentLayout(): Int {
        return R.layout.activity_category
    }

    override fun initView() {
        getDataFromBundle()
        getListCategoryFromBundle?.let { viewModel.getItemByCategory(category = it) }
        setupAdapter()
        setUpToolbar()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {
        viewModel.apply {
            itemByCategorise.observe(this@CategoryActivity) { result ->
                Log.d("GET_ALL_CATEGORY","$result")
                handleResultWithoutLoading(result, onSuccess = {
                    listItemByCategory.addAll(it)
                    itemCategoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun getDataFromBundle() {
        getNameCategoryFromBundle = intent.getStringExtra(CATEGORY).toString()
        getListCategoryFromBundle = intent.getSerializableExtra(LIST_CATEGORY) as Category
        Log.d("TAG", "getDataFromBundle: $getListCategoryFromBundle")
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = getNameCategoryFromBundle
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAdapter() {
        itemCategoryAdapter = ItemCategoryAdapter(
            this,
            listItemByCategory,
            onClickItemCategoryListener = { position, item ->
                Toast.makeText(this, "hihihi", Toast.LENGTH_SHORT).show()
            })
        setupLinearLayoutRecyclerView(this, binding.rvItemCategory)
        binding.rvItemCategory.adapter = itemCategoryAdapter
    }

}