package com.example.householdappliances.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Customer
import com.example.householdappliances.databinding.FragmentCreateAccountBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_create_account
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.tvCancle.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnSignup.setOnClickListener {
            val objCreateAccount = Customer()
            objCreateAccount.name = binding.tvName.text.toString().trim()
            objCreateAccount.username = binding.userName.text.toString().trim()
            objCreateAccount.tel = binding.tvTel.text.toString().trim()
            objCreateAccount.password = binding.suPassword.text.toString().trim()

            if (binding.tvName.text.toString().trim().isNotEmpty() &&
                binding.userName.text.toString().trim().isNotEmpty() &&
                binding.tvTel.text.toString().trim().isNotEmpty() &&
                binding.suPassword.text.toString().trim().isNotEmpty() &&
                binding.suCfpassword.text.toString().trim().isNotEmpty()) {

                if (binding.suPassword.text.toString().trim() == binding.suCfpassword.text.toString().trim()) {
                    sessionContext().userName = binding.userName.text.toString().trim()
                    sessionContext().password = binding.suPassword.text.toString().trim()
                    mainViewModel.createAccount(customer = objCreateAccount)
                    binding.tvName.text?.clear()
                    binding.userName.text?.clear()
                    binding.tvTel.text?.clear()
                    binding.suPassword.text?.clear()
                    binding.suCfpassword.text?.clear()
                    Toast.makeText(requireContext(), "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show()
                    navigationManager.gotoMainActivityScreen()
                } else Toast.makeText(requireContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Vui lòng điền đầy đủ thông tin",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvName.text?.clear()
        binding.userName.text?.clear()
        binding.tvTel.text?.clear()
        binding.suPassword.text?.clear()
        binding.suCfpassword.text?.clear()
    }

    override fun observerLiveData() {
        mainViewModel.apply {
            createAccountLogin.observe(this@CreateAccountFragment) { result ->
                handleResult(result, onSuccess = {
                    Log.d("TAG", "observerLiveData: $it")
                })
            }
        }
    }
}