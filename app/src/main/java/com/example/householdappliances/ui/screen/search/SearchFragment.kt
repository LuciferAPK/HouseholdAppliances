package com.example.householdappliances.ui.screen.search

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.householdappliances.R
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentSearchBinding
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.screen.cart.CartViewModel
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.utils.KeyboardUtils
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private val searchViewModel : SearchViewModel by activityViewModels()
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private val listItem: ArrayList<Item> = ArrayList()
    private val cartViewModel : CartViewModel by viewModels()
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: MainViewModel by activityViewModels()

    override fun getContentLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
        setupAdapter()
    }

    override fun initListener() {

        binding.toolbar.edtSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(
                editText: TextView?,
                actionId: Int,
                p2: KeyEvent?
            ): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (binding.toolbar.edtSearch.text.trim().isNotEmpty()) {
                        listItem.clear()
                        detailCategoryAdapter.notifyDataSetChanged()
                        val keySearch = binding.toolbar.edtSearch.text.trim()
                        searchViewModel.searchItemByKey(keySearch.toString())
                    }
                    KeyboardUtils.hideKeyboard(activity)
                    return true
                }
                return false
            }
        })

        binding.toolbar.icClearText.setOnClickListener {
            binding.toolbar.edtSearch.setText("")
            KeyboardUtils.hideKeyboard(activity)
            binding.toolbar.edtSearch.clearFocus()
        }
    }

    override fun observerLiveData() {
        searchViewModel.apply {
            searchItemResult.observe(this@SearchFragment) { result ->
                handleResult(
                    result,
                    onSuccess = {
                        listItem.addAll(it)
                        detailCategoryAdapter.notifyDataSetChanged()
                    }
                )
            }
        }
    }

    private fun setupAdapter() {
        detailCategoryAdapter = DetailCategoryAdapter(
            requireContext(),
            listItem,
            onClickItemCategoryListener = { position, item ->
                navigationManager.gotoDetailActivityScreen(item)
            },
            onClickAddToCartListener = { position, item ->
                var cartItem = CartItem()
                cartItem.item = item
                val objAddToCart = Cart()
                objAddToCart.amount = 1
                objAddToCart.createdTime = System.currentTimeMillis()
                objAddToCart.totalPrice = item.price?.toLong()
//                viewModel.addCartItemToCart(cart = objAddToCart)
                if(ApplicationContext.customer == null){
                    navigationManager.gotoLoginActivityScreen()
                }else{
                    if(ApplicationContext.cart == null){
                        cartViewModel.getCartOfCustomer()
                    }else{
                        ApplicationContext.cart?.cartItems?.add(cartItem)
                        viewModel.addCartItemToCart(cart = ApplicationContext.cart)
                        Toast.makeText(requireContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        setupLinearLayoutRecyclerView(context, binding.rvResultSearch)
        binding.rvResultSearch.adapter = detailCategoryAdapter
    }
}