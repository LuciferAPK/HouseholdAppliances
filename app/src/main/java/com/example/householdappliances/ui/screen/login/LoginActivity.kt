package com.example.householdappliances.ui.screen.login

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityLoginBinding
import com.example.householdappliances.databinding.ActivitySplashBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignup.setOnClickListener {
            navigationManager.gotoCreateAccountFragmentScreen(supportFragmentManager)
        }
    }

    override fun observerLiveData() {}

    override fun getLayoutLoading(): View? {
        return null
    }
}