package com.example.householdappliances.ui.screen.main

import androidx.lifecycle.ViewModel
import com.example.householdappliances.base.SingleLiveEvent
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.repository.CategoryRepository
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.Customer
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.network.*
import com.example.householdappliances.repository.CartRepository
import com.example.householdappliances.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val homeRepository: HomeRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    val categoriseResult = SingleLiveEvent<Result<List<Category>>>()
    fun getAllCategory(
        url: String? = END_POINT_GET_ALL_CATEGORY
    ) {
        val request = categoryRepository.getAllCategories(
            url = url
        )
        categoriseResult.addSource(request) {
            categoriseResult.postValue(it)
        }
    }

    val itemByCategorise = SingleLiveEvent<Result<List<Item>>>()
    fun getItemByCategory(
        url: String? = END_POINT_GET_ITEM_BY_CATEGORY,
        idCategory: Int
    ) {
        val request = categoryRepository.getItemByCategories(
            url = url,
            idCategory = idCategory
        )
        itemByCategorise.addSource(request) {
            itemByCategorise.postValue(it)
        }
    }

    val accountLogin = SingleLiveEvent<Result<Customer>>()
    fun getAccountLogin(
        url: String? = END_POINT_CHECK_LOGIN,
        username: String? = null,
        password: String? = null
    ) {
        val request = homeRepository.getAllAccountLogin(
            url = url,
            username = username,
            password = password
        )
        accountLogin.addSource(request) {
            accountLogin.postValue(it)
        }
    }

    val createAccountLogin = SingleLiveEvent<Result<Customer>>()
    fun createAccount(
        url: String? = END_POINT_CREATE_CUSTOMER,
        customer: Customer
    ) {
        val request = homeRepository.createAccount(
            url = url,
            customer = customer
        )
        createAccountLogin.addSource(request) {
            createAccountLogin.postValue(it)
        }
    }

    val getCartByIdCustomer = SingleLiveEvent<Result<Cart>>()
    fun getCartOfCustomer(
        url: String? = END_POINT_GET_CART_OF_CUSTOMER,
        id: Int
    ) {
        val request = cartRepository.getCartOfCustomer(
            url = url,
            id = id
        )
        getCartByIdCustomer.addSource(request) {
            getCartByIdCustomer.postValue(it)
        }
    }

    val addCartItemToCartCustomer = SingleLiveEvent<Result<Cart>>()
    fun addCartItemToCart(
        url: String? = END_POINT_ADD_CART_ITEM_TO_CART,
        cart: Cart
    ) {
        val request = cartRepository.addCartItemToCart(
            url = url,
            cart = cart
        )
        addCartItemToCartCustomer.addSource(request) {
            addCartItemToCartCustomer.postValue(it)
        }
    }
}