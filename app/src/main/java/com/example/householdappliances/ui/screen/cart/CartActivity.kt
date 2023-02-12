package com.example.householdappliances.ui.screen.cart

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentCartBinding
import com.example.householdappliances.navigation.KeyDataIntent
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCartAdapter
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : BaseActivity<FragmentCartBinding>() {
    private var cart : Cart?= null
    private val cartViewModel: CartViewModel by viewModels()
    private val listItem: ArrayList<Item> = ArrayList()
    private val cartItem: ArrayList<CartItem?> = arrayListOf()
    private lateinit var detailCartAdapter: DetailCartAdapter
    private var totalAmount: Int ?= 0
    private var totalPrice = 0L
    private var currentPositionDelete = -1

    @Inject
    lateinit var navigationManager: NavigationManager
    override fun getContentLayout(): Int {
        return R.layout.fragment_cart
    }

    override fun initView() {
        setupAdapter()
        getDataFromIntent()
    }

    override fun initListener() {
        binding.btnOrder.setOnClickListener {
            cart?.amount = totalAmount
            cart?.totalPrice = totalPrice
            navigationManager.gotoOrderActivityScreen(this, cart)
        }
        binding.deleteList.setOnClickListener {
            cartViewModel.deleteCart(idCart = cart?.id)
        }
    }

    private fun getDataFromIntent(){
        if(intent.hasExtra(KeyDataIntent.CART)){
            cart = intent.getSerializableExtra(KeyDataIntent.CART) as Cart
            cartItem.apply {
                clear()
                cart?.cartItems?.let { addAll(it) }
            }
            detailCartAdapter.notifyDataSetChanged()
            calculatorTotalPriceAndTotalAmount()
        }
        else {
            cartViewModel.getCartOfCustomer()
        }
    }

    private fun calculatorTotalPriceAndTotalAmount(){
        totalAmount = 0
        totalPrice = 0
        cartItem.forEach {
            totalAmount = totalAmount?.plus(it?.amount ?: 0)
            totalPrice = totalPrice.plus(it?.amount?.times(it.item?.price!!) ?: 0)
        }
        binding.tvTotalAmount.text = totalAmount.toString()
        binding.tvTotalPrice.text = totalPrice.toString()
        binding.tvTotal.text = totalPrice.toString()
    }

    private fun setupAdapter() {
        detailCartAdapter = DetailCartAdapter(
            this,
            cartItem,
            onClickDeleteItemListener = {i, item ->
                currentPositionDelete = i
                cartViewModel.deleteCartItem(idCartItem = item?.id)
            })
        setupLinearLayoutRecyclerView(this, binding.rvListCart)
        binding.rvListCart.adapter = detailCartAdapter
    }

    override fun observerLiveData() {
        cartViewModel.apply {
            getCartByIdCustomer.observe(this@CartActivity){ result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progress.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        cart = result.data
                        cartItem.clear()
                        cart?.cartItems?.let { cartItem.addAll(it) }
                        detailCartAdapter.notifyDataSetChanged()

                        binding.progress.visibility = View.GONE
                        calculatorTotalPriceAndTotalAmount()
                    }
                    else ->{
                    }
                }
            }

            deleteCartItemResult.observe(this@CartActivity){ result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progress.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.progress.visibility = View.GONE
                        cartItem.removeAt(currentPositionDelete)
                        cart?.cartItems?.removeAt(currentPositionDelete)
                        ApplicationContext.cart?.cartItems?.removeAt(currentPositionDelete)
                        calculatorTotalPriceAndTotalAmount()
                        detailCartAdapter.notifyDataSetChanged()
                    }
                    else ->{
                        binding.progress.visibility = View.GONE
                    }
                }
            }

            deleteCartResult.observe(this@CartActivity){ result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progress.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.progress.visibility = View.GONE
                        cartItem.clear()
                        ApplicationContext.cart = null
                        cart = null
                        calculatorTotalPriceAndTotalAmount()
                        detailCartAdapter.notifyDataSetChanged()
                    }
                    else ->{
                    }
                }
            }

        }
    }
}