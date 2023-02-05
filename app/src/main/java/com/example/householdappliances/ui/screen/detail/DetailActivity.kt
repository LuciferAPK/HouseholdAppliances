package com.example.householdappliances.ui.screen.detail

import android.annotation.SuppressLint
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.ActivityDetailBinding
import com.example.householdappliances.navigation.KeyDataIntent
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.layout_item_by_card.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val cartViewModel: CartViewModel by viewModels()

    private var item: Item?= null
    private var currentCartItem : CartItem ?= null

    override fun getContentLayout(): Int {
        return R.layout.activity_detail
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        getDataFromIntent()
        binding.txtTitle.text = "Thông tin sản phẩm"
        initData()
    }

    private fun getDataFromIntent(){
        if(intent.hasExtra(KeyDataIntent.ITEM)){
            item = intent.getSerializableExtra(KeyDataIntent.ITEM) as Item
        }
    }

    private fun initData(){
        Glide.with(this).load(item?.image).into(binding.imgDetail)
        binding.tvNameProduct.text = item?.name
        binding.tvCategory.text = item?.category?.name
        binding.tvPrice.text = item?.price.toString()
        binding.tvDescriptions.text = item?.descriptions
    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.imgBuy.setOnClickListener {
            /**check login*/
            if(ApplicationContext.customer == null)
                navigationManager.gotoLoginActivityScreen()
            else{
                navigationManager.gotoCartActivityScreen(this)
            }
        }
        binding.addToCart.setOnClickListener {
            try {
                val amount = binding.edtAmount.text.trim().toString().toInt()
                if(ApplicationContext.customer == null)
                    navigationManager.gotoLoginActivityScreen()
                else {
                    currentCartItem = CartItem(amount = amount, createdTime = System.currentTimeMillis(), item = item)
                    if(ApplicationContext.cart == null){
                        cartViewModel.getCartOfCustomer()
                    }
                    else{
                        ApplicationContext.cart?.cartItems?.add(currentCartItem)
                        cartViewModel.addCartItemToCart(cart = ApplicationContext.cart)
                    }
                }
            } catch (e: Exception){
                Toast.makeText(this, "Nhập số lượng mặt hàng", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun observerLiveData() {
        cartViewModel.apply {
            getCartByIdCustomer.observe(this@DetailActivity){ result ->
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

            updateCartResult.observe(this@DetailActivity){ result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        binding.progress.visibility = View.GONE
                        ApplicationContext.cart = result.data
                        Log.d("CART_CUSTOMER", result.data.cartItems.toString())
                        navigationManager.gotoCartActivityScreen(this@DetailActivity, result.data)
                    }
                    is Result.Failure->{
                        Log.d("CART_CUSTOMER", "ERROR - ${result.failureMessage}")
                        binding.progress.visibility = View.GONE
                    }
                }

            }

        }
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}