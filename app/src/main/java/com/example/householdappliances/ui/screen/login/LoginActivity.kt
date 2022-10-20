package com.example.householdappliances.ui.screen.login

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityLoginBinding
import com.example.householdappliances.databinding.ActivitySplashBinding
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.utils.CoroutineExt

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        startMain()
    }

    override fun initListener() {}

    override fun observerLiveData() {}

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun startMain() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignup.setOnClickListener {

        }
    }
}