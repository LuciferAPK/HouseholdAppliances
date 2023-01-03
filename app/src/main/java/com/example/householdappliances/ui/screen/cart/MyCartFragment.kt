package com.example.householdappliances.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentCartBinding
import com.example.householdappliances.dialog.DialogClearCart
import com.example.householdappliances.ui.screen.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCartFragment : BaseFragment<FragmentCartBinding>() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_cart
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.titleCart.text = "Giỏ hàng"
    }

    override fun initListener() {
        binding.deleteList.setOnClickListener {
            DialogClearCart().show(childFragmentManager, "DialogClearCart")
        }
    }

    override fun observerLiveData() {
        mainViewModel.apply {
            getCartByIdCustomer.observe(this@MyCartFragment) { result ->
                handleResult(result, onSuccess = {
                    Log.d("TAG", "observerLiveData: ${it.cartItems}")
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (sessionContext().id != 0) {
            mainViewModel.getCartOfCustomer(id = sessionContext().id!!)
        }
    }
}