package com.example.householdappliances.ui.screen.account

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.databinding.FragmentAccountBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.fragment_account
    }

    override fun initView() {
        CoroutineExt.runOnMainAfterDelay(300) {
            setupData()
        }
    }

    override fun initListener() {
        binding.btnLogout.setOnClickListener {
            navigationManager.gotoLoginActivityScreen()
        }

        binding.contractShip.setOnClickListener {
            navigationManager.gotoAddressFragmentScreen(parentFragmentManager)
        }

        binding.listItem.setOnClickListener {
            navigationManager.gotoListCartFragmentScreen(parentFragmentManager)
        }
    }

    override fun observerLiveData() {

    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        binding.nameInformation.text = "Họ tên: ${ApplicationContext.customer?.name}"
        binding.numberInformation.text = "Điện thoại: ${ApplicationContext.customer?.tel}"
    }
}