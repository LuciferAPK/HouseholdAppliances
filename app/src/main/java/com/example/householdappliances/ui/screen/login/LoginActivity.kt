package com.example.householdappliances.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityLoginBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private var isCheckLogin = false
    private lateinit var userName: String
    private lateinit var password: String
    private val mainViewModel : MainViewModel by viewModels()

    override fun getContentLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            userName = binding.tvTk.text.toString().trim()
            password = binding.tvPass.text.toString().trim()
            mainViewModel.getAccountLogin(username = userName, password = password)
            CoroutineExt.runOnMainAfterDelay(500) {
                if (isCheckLogin) {
                    if (userName != sessionContext().userName || password != sessionContext().password) {
                        Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show()
                    } else {
                        navigationManager.gotoMainActivityScreen()
                    }
                }
            }
        }

        binding.tvSignup.setOnClickListener {
            navigationManager.gotoCreateAccountFragmentScreen(supportFragmentManager)
        }
    }

    override fun observerLiveData() {
        mainViewModel.apply {
            accountLogin.observe(this@LoginActivity) { result ->
                handleResult(result, onSuccess = {
                    if (it.email?.isNotEmpty() == true) {
                        isCheckLogin = true
                        sessionContext().id = it.id
                        sessionContext().tel = it.tel
                        sessionContext().email = it.email
                        sessionContext().name = it.name
                        sessionContext().password = it.password
                        sessionContext().userName = it.username
                    } else {
                        isCheckLogin = false
                    }
                })
            }
        }
    }
}