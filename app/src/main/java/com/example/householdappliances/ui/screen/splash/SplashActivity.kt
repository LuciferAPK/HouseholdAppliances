package com.example.householdappliances.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivitySplashBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.login.LoginActivity
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel : MainViewModel by viewModels()

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        ApplicationContext.customer = viewModel.getCustomer()
        CoroutineExt.runOnMainAfterDelay(2000) {
            navigationManager.gotoMainActivityScreen()
        }
    }

    override fun initListener() {}

    override fun observerLiveData() {}
}