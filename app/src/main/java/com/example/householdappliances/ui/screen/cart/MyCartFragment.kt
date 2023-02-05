package com.example.householdappliances.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentCartBinding
import com.example.householdappliances.dialog.DialogClearCart
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCartAdapter
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.setupGridLayoutRecyclerView
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyCartFragment : BaseFragment<FragmentCartBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private var cart : Cart?= null
    private val cartItem: ArrayList<CartItem?> = ArrayList()
    private lateinit var detailCartAdapter: DetailCartAdapter
    private var totalAmount: Int ?= 0
    private var totalPrice = 0L

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_cart
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.titleCart.text = "Giỏ hàng"
        setupAdapter()
    }

    override fun initListener() {
        binding.deleteList.setOnClickListener {
            DialogClearCart().show(childFragmentManager, "DialogClearCart")
        }

        binding.btnOrder.setOnClickListener {
            cart?.amount = totalAmount
            cart?.totalPrice = totalPrice
            navigationManager.gotoOrderActivityScreen(requireActivity(), cart)
        }
    }

    override fun observerLiveData() {
        cartViewModel.apply {
            getCartByIdCustomer.observe(this@MyCartFragment){ result ->
                when(result){
                    is Result.InProgress ->{

                    }
                    is Result.Success ->{
                        cart = result.data
                        cartItem.clear()
                        cart?.cartItems?.let { cartItem.addAll(it) }
                        detailCartAdapter.notifyDataSetChanged()
                        calculatorTotalPriceAndTotalAmount()
                    }
                    else ->{
                    }
                }
            }

        }
    }

    private fun calculatorTotalPriceAndTotalAmount(){
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
            requireContext(),
            cartItem,
            onClickDeleteItemListener = {i, item ->
                Toast.makeText(requireContext(), "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show()
            })
        setupLinearLayoutRecyclerView(context, binding.rvListCart)
        binding.rvListCart.adapter = detailCartAdapter
    }

    override fun onResume() {
        super.onResume()
        if (cartItem.isEmpty()) {
            cartViewModel.getCartOfCustomer()
        }
    }
}