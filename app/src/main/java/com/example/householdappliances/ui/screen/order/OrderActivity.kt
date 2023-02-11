package com.example.householdappliances.ui.screen.order

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.data.model.*
import com.example.householdappliances.databinding.ActivityOrderBinding
import com.example.householdappliances.navigation.KeyDataIntent
import com.example.householdappliances.ui.adapter.CRUDSpinnerAdapter
import com.example.householdappliances.ui.adapter.DetailCartAdapter
import com.example.householdappliances.ui.screen.cart.CartViewModel
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import com.example.householdappliances.base.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : BaseActivity<ActivityOrderBinding>() {
    private var cart: Cart? = null
    private val cartViewModel: CartViewModel by viewModels()
    private val listItem: ArrayList<Item> = ArrayList()
    private val cartItem: ArrayList<CartItem?> = arrayListOf()
    private lateinit var detailCartAdapter: DetailCartAdapter

    private val listPayment: ArrayList<ItemSpinner> = arrayListOf()
    private lateinit var paymentAdapter: CRUDSpinnerAdapter
    override fun getContentLayout(): Int {
        return R.layout.activity_order
    }

    override fun initView() {
        getDataFromIntent()
        setupAdapter()
        setData()
    }

    private fun setData(){
        binding.tvTotalAmount.text = cart?.amount.toString()
        binding.tvTotalPrice.text = cart?.totalPrice.toString()
        binding.tvTotal.text = cart?.totalPrice.toString()
        binding.tvAddress.text = cart?.customer?.address?.toString()
    }

    private fun getDataFromIntent() {
        if (intent.hasExtra(KeyDataIntent.CART)) {
            cart = intent.getSerializableExtra(KeyDataIntent.CART) as Cart
            cartItem.apply {
                clear()
                cart?.cartItems?.let { addAll(it) }
            }
        }
    }

    private fun setupAdapter() {
        detailCartAdapter = DetailCartAdapter(
            this,
            cartItem,
            onClickDeleteItemListener = { i, item ->
                Toast.makeText(this, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show()
            }, isDelete = false)
        setupLinearLayoutRecyclerView(this, binding.rvListCart)
        binding.rvListCart.adapter = detailCartAdapter

        listPayment.apply {
            add(ItemSpinner(title = "VISA"))
            add(ItemSpinner(title = "Tài khoản ngân hàng"))
            add(ItemSpinner(title = "Momo"))
            add(ItemSpinner(title = "Thanh toán khi nhận hàng"))
        }
        paymentAdapter = CRUDSpinnerAdapter(listPayment)
        binding.spinnerChoosePayment.adapter = paymentAdapter
    }

    override fun initListener() {
        binding.btnOrder.setOnClickListener {
            var order = Order(
                createdTime = System.currentTimeMillis(),
                totalAmount = cart?.amount,
                totalPrice = cart?.totalPrice,
                payment = listPayment[binding.spinnerChoosePayment.selectedItem as Int].title,
                status = "Đang chờ xử lý",
                finalPrice = cart?.totalPrice,
                cart = cart
            )
            order.cart?.isOrder = "yes"
            cartViewModel.takeOrder(order = order)
        }

    }

    override fun observerLiveData() {
        cartViewModel.apply {
            takeOrderResult.observe(this@OrderActivity){result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        Toast.makeText(this@OrderActivity, "SUCCESS ${result.data.id}", Toast.LENGTH_SHORT).show()
                    }
                    else ->{
                    }
                }

            }
        }
    }

}