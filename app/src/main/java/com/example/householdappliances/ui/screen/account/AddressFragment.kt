package com.example.householdappliances.ui.screen.account

import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentAddressBinding
import com.example.householdappliances.databinding.FragmentHomeBinding

class AddressFragment : BaseFragment<FragmentAddressBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_address
    }

    override fun initView() {
        setupToolbar()
        this.setHasOptionsMenu(true)
        onBackStack()
    }

    override fun initListener() {
        binding.txtAddress.text = ApplicationContext.customer?.address.toString()
    }

    override fun observerLiveData() {

    }

    private fun onBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Địa chỉ giao hàng"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}