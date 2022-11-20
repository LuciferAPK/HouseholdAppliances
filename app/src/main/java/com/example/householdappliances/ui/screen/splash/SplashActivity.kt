package com.example.householdappliances.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivitySplashBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.login.LoginActivity
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        CoroutineExt.runOnMainAfterDelay(2000) {
            navigationManager.gotoMainActivityScreen()
        }
    }

    override fun initListener() {}

    override fun observerLiveData() {}

    override fun getLayoutLoading(): View? {
        return null
    }

}