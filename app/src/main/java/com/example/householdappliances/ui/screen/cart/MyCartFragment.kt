package com.example.householdappliances.ui.screen.cart

import android.annotation.SuppressLint
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentCartBinding
import com.example.householdappliances.dialog.DialogClearCart

class MyCartFragment : BaseFragment<FragmentCartBinding>() {
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

    }
}