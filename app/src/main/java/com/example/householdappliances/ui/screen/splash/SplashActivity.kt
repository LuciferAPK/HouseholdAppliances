package com.example.householdappliances.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivitySplashBinding
import com.example.householdappliances.ui.screen.login.LoginActivity
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.utils.CoroutineExt

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
//        startProcessData()
        CoroutineExt.runOnMainAfterDelay(2000) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun initListener() {}

    override fun observerLiveData() {}

    override fun getLayoutLoading(): View? {
        return null
    }

//    private fun startProcessData() {
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.getWallpapers()
//        viewModel.mLiveData.observe(this) { data ->
//            if (data != null) {
//                Log.d("TAG", "initViewModel: $data")
//            }
//        }
//    }
}