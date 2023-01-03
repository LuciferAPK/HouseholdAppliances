package com.example.householdappliances.ui.screen.account

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentAccountBinding
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.CoroutineExt

class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override fun getContentLayout(): Int {
        return R.layout.fragment_account
    }

    override fun initView() {
        CoroutineExt.runOnMainAfterDelay(300) {
            setupData()
        }
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        binding.nameInformation.text = "Họ tên: ${sessionContext().name}"
        binding.numberInformation.text = "Điện thoại: ${sessionContext().tel}"
        binding.emailInformation.text = "Email: ${sessionContext().email}"
    }
}