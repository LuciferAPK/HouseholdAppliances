package com.example.householdappliances.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import kotlinx.android.synthetic.main.layout_request_login.*
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
    private var currentPositionDelete = -1

    private val cartViewModel: CartViewModel by viewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_cart
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.titleCart.text = "Giỏ hàng"
        cart = ApplicationContext.cart
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
        binding.viewRequestLogin.btnLogin.setOnClickListener {
            navigationManager.gotoLoginActivityScreen()
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
                        ApplicationContext.cart = result.data
                        cart?.cartItems?.let { cartItem.addAll(it) }
                        detailCartAdapter.notifyDataSetChanged()
                        calculatorTotalPriceAndTotalAmount()
                    }
                    else ->{
                    }
                }
            }
            deleteCartItemResult.observe(this@MyCartFragment){ result ->
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
            requireContext(),
            cartItem,
            onClickDeleteItemListener = {i, item ->
                currentPositionDelete = i
                cartViewModel.deleteCartItem(idCartItem = item?.id)
            })
        setupLinearLayoutRecyclerView(context, binding.rvListCart)
        binding.rvListCart.adapter = detailCartAdapter
    }

    override fun onResume() {
        super.onResume()
        if (ApplicationContext.customer == null) {
            binding.viewRequestLogin.root.visibility = View.VISIBLE
        }
        else{
            cartViewModel.getCartOfCustomer()
            binding.viewRequestLogin.root.visibility = View.GONE
        }
    }
}