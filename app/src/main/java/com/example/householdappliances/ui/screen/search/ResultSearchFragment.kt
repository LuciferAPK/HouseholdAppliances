package com.example.householdappliances.ui.screen.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentResultSearchBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.cart.CartViewModel
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
    private var currentCartItem: CartItem ?= null
    private val cartViewModel : CartViewModel by activityViewModels()
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
        cartViewModel.apply {
            getCartByIdCustomer.observe(this@ResultSearchFragment){ result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progress.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        ApplicationContext.cart = result.data
                        val cart = result.data
                        cart.cartItems?.add(currentCartItem)
                        cartViewModel.addCartItemToCart(cart = cart)
                    }
                    is Result.Failure->{
                        Log.d("CART_CUSTOMER", "ERROR - ${result.failureMessage}")
                        binding.progress.visibility = View.GONE
                    }
                }
            }

            updateCartResult.observe(this@ResultSearchFragment){ result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        binding.progress.visibility = View.GONE
                        ApplicationContext.cart = result.data
                        Log.d("CART_CUSTOMER", result.data.cartItems.toString())
                        //navigationManager.gotoCartActivityScreen(this@DetailActivity, result.data)
                        Toast.makeText(requireContext(),"Thêm mặt hàng thành công vào giỏ hàng", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Failure->{
                        Log.d("CART_CUSTOMER", "ERROR - ${result.failureMessage}")
                        binding.progress.visibility = View.GONE
                    }
                }

            }

        }
    }

    private fun setupAdapter() {
        detailCategoryAdapter = DetailCategoryAdapter(
            requireContext(),
            listItem,
            onClickItemCategoryListener = { position, item ->
                navigationManager.gotoDetailActivityScreen(item)
            },
            onClickAddToCartListener = { position, item ->
                currentCartItem = CartItem(amount = 1, createdTime = System.currentTimeMillis(), item = item)
                if(ApplicationContext.customer == null){
                    navigationManager.gotoLoginActivityScreen()
                }else{
                    if(ApplicationContext.cart == null){
                        cartViewModel.getCartOfCustomer()
                    }else{
                        ApplicationContext.cart?.cartItems?.add(currentCartItem)
                        cartViewModel.addCartItemToCart(cart = ApplicationContext.cart)
                    }
                }
            })
        setupLinearLayoutRecyclerView(context, binding.rvItemResultSearch)
        binding.rvItemResultSearch.adapter = detailCategoryAdapter
    }
}