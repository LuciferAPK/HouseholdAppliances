package com.example.householdappliances.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
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
    private val listItem: ArrayList<Item> = ArrayList()
    private val cartItem: ArrayList<CartItem?> = ArrayList()
    private lateinit var detailCartAdapter: DetailCartAdapter

    private val mainViewModel: MainViewModel by activityViewModels()

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
    }

    override fun observerLiveData() {
//        mainViewModel.apply {
//            getCartByIdCustomer.observe(this@MyCartFragment) { result ->
//                Log.d("TAG", "observerLiveData: $result")
//                handleResult(result, onSuccess = {
//                    it.cartItems?.let { it1 -> cartItem.addAll(it1.toList()) }
//                    detailCartAdapter.notifyDataSetChanged()
////                    it.cartItems?.let { it1 -> cartItem.addAll(it1) }
////                    for (cart in cartItem) {
////                        cart?.item?.let { it1 -> listItem.add(it1) }
//////                        Log.d("TAG", "observerLiveData: ${it.cartItems?.size}")
////                    }
////                    Log.d("TAG", "observerLiveData: ${it.cartItems[0].item}")
//                })
//            }
//        }
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

//    override fun onResume() {
//        super.onResume()
//        if (sessionContext().id != 0) {
//            mainViewModel.getCartOfCustomer(id = sessionContext().id!!)
//        }
//    }
}