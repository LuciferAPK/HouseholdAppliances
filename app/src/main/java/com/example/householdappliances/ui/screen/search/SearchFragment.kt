package com.example.householdappliances.ui.screen.search

import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseFragment
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.FragmentSearchBinding
import com.example.householdappliances.enums.VPControlSearchScreenType
import com.example.householdappliances.navigation.NavigationManager
import com.example.householdappliances.ui.adapter.DetailCategoryAdapter
import com.example.householdappliances.ui.adapter.HomePageAdapter
import com.example.householdappliances.ui.screen.main.MainViewModel
import com.example.householdappliances.ui.screen.search.extension.showIcSearch
import com.example.householdappliances.utils.KeyboardUtils
import com.example.householdappliances.utils.setupLinearLayoutRecyclerView
import java.util.ArrayList
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private val searchViewModel : SearchViewModel by activityViewModels()
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private val listItem: ArrayList<Item> = ArrayList()
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
                viewModel.addCartItemToCart(cart = objAddToCart)
                Toast.makeText(requireContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
            })
        setupLinearLayoutRecyclerView(context, binding.rvResultSearch)
        binding.rvResultSearch.adapter = detailCategoryAdapter
    }
}