package com.example.householdappliances.ui.screen.cart

import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentListCartBinding

class ListCartFragment : BaseFragment<FragmentListCartBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_list_cart
    }

    override fun initView() {
        setupToolbar()
        this.setHasOptionsMenu(true)
        onBackStack()
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).title = "Danh sách đơn hàng"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun onBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }
}