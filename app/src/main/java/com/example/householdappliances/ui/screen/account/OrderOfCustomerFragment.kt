package com.example.householdappliances.ui.screen.account

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Order
import com.example.householdappliances.databinding.FragmentOrderOfCustomerBinding
import com.example.householdappliances.ui.adapter.OrderAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderOfCustomerFragment : BaseFragment<FragmentOrderOfCustomerBinding>() {
    private val listOrder : ArrayList<Order?> = arrayListOf()
    private lateinit var adapter: OrderAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_order_of_customer
    }

    override fun initView() {
        viewModel.getOrderOfCustomer(idCustomer = ApplicationContext.customer?.id)
        adapter = OrderAdapter(listOrder)
        binding.rvOrder.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvOrder.adapter = adapter
        onBackStack()

    }

    private fun onBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }

    override fun initListener() {
    }

    override fun observerLiveData() {
        viewModel.apply {
            orderOfCustomerResult.observe(this@OrderOfCustomerFragment){ result ->
                when(result){
                    is Result.InProgress ->{

                    }
                    is Result.Success ->{
                        listOrder.apply {
                            clear()
                            addAll(result.data)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    else ->{
                    }
                }

            }
        }
    }
}