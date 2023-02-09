package com.example.householdappliances.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext.sessionContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Address
import com.example.householdappliances.data.model.Customer
import com.example.householdappliances.databinding.FragmentCreateAccountBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.base.Result
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private var address: Address ?= null

    private var dialogAddress : DialogCreateAddress ?= null

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_create_account
    }

    override fun initView() {
        dialogAddress = DialogCreateAddress()
    }

    override fun initListener() {
        binding.tvCancle.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.tvAddress.setOnClickListener {
            dialogAddress?.show(parentFragmentManager, DialogCreateAddress::class.java.name)
        }

        binding.btnSignup.setOnClickListener {
            val objCreateAccount = Customer()
            objCreateAccount.name = binding.tvName.text.toString().trim()
            objCreateAccount.username = binding.userName.text.toString().trim()
            objCreateAccount.tel = binding.tvTel.text.toString().trim()
            objCreateAccount.password = binding.suPassword.text.toString().trim()
            objCreateAccount.address = address

            if (binding.tvName.text.toString().trim().isNotEmpty() &&
                binding.userName.text.toString().trim().isNotEmpty() &&
                binding.tvTel.text.toString().trim().isNotEmpty() &&
                binding.suPassword.text.toString().trim().isNotEmpty() &&
                binding.suCfpassword.text.toString().trim().isNotEmpty()) {

                if (binding.suPassword.text.toString().trim() == binding.suCfpassword.text.toString().trim()) {
                    sessionContext().userName = binding.userName.text.toString().trim()
                    sessionContext().password = binding.suPassword.text.toString().trim()
                    mainViewModel.createAccount(customer = objCreateAccount)
//                    binding.tvName.text?.clear()
//                    binding.userName.text?.clear()
//                    binding.tvTel.text?.clear()
//                    binding.suPassword.text?.clear()
//                    binding.suCfpassword.text?.clear()
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
            addressCustomer.observe(this@CreateAccountFragment){
                binding.tvAddress.setText(it.toString())
                address = it
            }
            createAccountLogin.observe(this@CreateAccountFragment){ result ->
                Log.d("TAG", "observerLiveData: $result")
                when(result){
                    is Result.InProgress -> {

                    }
                    is Result.Success -> {
                        Toast.makeText(requireContext(), "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    } else -> {

                    }
                }

            }
        }
    }
}