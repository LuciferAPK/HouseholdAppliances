package com.example.householdappliances.ui.screen.home.viewpager

import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.*
import com.example.householdappliances.databinding.FragmentCategoryBinding
import com.example.householdappliances.databinding.FragmentHomeBinding
import com.example.householdappliances.navigation.KeyDataIntent
import com.example.householdappliances.navigation.KeyDataIntent.CATEGORY
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel: MainViewModel by viewModels()
    private val listItemByCategory: ArrayList<Item> = ArrayList()
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private lateinit var category: Category

    override fun getContentLayout(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        getDataFromBundle()
        viewModel.getItemByCategory(idCategory = category.id!!)
        setupAdapter()
        setUpToolbar()
        this.setHasOptionsMenu(true)
        onBackStack()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {
        viewModel.apply {
            itemByCategorise.observe(this@CategoryFragment) { result ->
                handleResult(result, onSuccess = {
                    listItemByCategory.addAll(it)
                    detailCategoryAdapter.notifyDataSetChanged()
                })
            }

            addCartItemToCartCustomer.observe(this@CategoryFragment) { result ->
                Log.d("TAG", "observerLiveData: $result")
                handleResult(result, onSuccess = {
                    Log.d("TAG", "observerLiveData: $it")
                })
            }
        }
    }

    private fun getDataFromBundle() {
        category = arguments?.getSerializable(CATEGORY) as Category
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        Objects.requireNonNull((activity as AppCompatActivity).supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = category.name
    }

    private fun onBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        parentFragmentManager.popBackStack()
        return super.onOptionsItemSelected(item)
    }

    private fun setupAdapter() {
        detailCategoryAdapter = DetailCategoryAdapter(
            requireContext(),
            listItemByCategory,
            onClickItemCategoryListener = { position, item ->
                navigationManager.gotoDetailActivityScreen()
            },
            onClickAddToCartListener = { position, item ->
                /**check login*/
                var cartItem = CartItem()
                cartItem.item = item
                val objAddToCart = Cart()
//                objAddToCart.cartItems = listItemByCategory.get(0)
                objAddToCart.amount = 1
                objAddToCart.createdTime = System.currentTimeMillis()
                objAddToCart.totalPrice = item.price?.toLong()
//                objAddToCart.cartItems = cartItem
                viewModel.addCartItemToCart(cart = objAddToCart)
                Toast.makeText(requireContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
            })
        setupLinearLayoutRecyclerView(context, binding.rvItemCategory)
        binding.rvItemCategory.adapter = detailCategoryAdapter
    }

    private fun getDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.now().format(formatter)
    }
}