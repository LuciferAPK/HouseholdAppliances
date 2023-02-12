package com.example.householdappliances.ui.screen.main

import androidx.lifecycle.ViewModel
import com.example.householdappliances.base.SingleLiveEvent
import com.example.householdappliances.repository.CategoryRepository
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.*
import com.example.householdappliances.network.*
import com.example.householdappliances.preferences.CUSTOMER
import com.example.householdappliances.preferences.PreferencesManager
import com.example.householdappliances.repository.CartRepository
import com.example.householdappliances.repository.HomeRepository
import com.example.householdappliances.utils.GsonUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val homeRepository: HomeRepository,
    private val cartRepository: CartRepository,
    private val preferencesManager: PreferencesManager
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

    val addCartItemToCartCustomer = SingleLiveEvent<Result<Cart>>()
    fun addCartItemToCart(
        url: String? = END_POINT_ADD_CART_ITEM_TO_CART,
        cart: Cart?
    ) {
        val request = cartRepository.addCartItemToCart(
            url = url,
            cart = cart
        )
        addCartItemToCartCustomer.addSource(request) {
            addCartItemToCartCustomer.postValue(it)
        }
    }

    val orderOfCustomerResult = SingleLiveEvent<Result<List<Order>>>()
    fun getOrderOfCustomer(
        url: String? = END_POINT_GET_ALL_ORDER_OF_CUSTOMER,
        idCustomer: Int? = 0
    ) {
        val request = homeRepository.getAllOrderOfCustomer(
            url = url,
            idCustomer = idCustomer
        )
        orderOfCustomerResult.addSource(request) {
            orderOfCustomerResult.postValue(it)
        }
    }

    fun saveCustomer(customer: Customer) = preferencesManager.save(CUSTOMER, GsonUtils.serialize(customer, Customer::class.java))

    fun getCustomer() : Customer? = GsonUtils.deserialize(preferencesManager.getString(CUSTOMER), Customer::class.java)

    fun clearCustomer() = preferencesManager.remove(CUSTOMER)

    val addressCustomer = SingleLiveEvent<Address>()
    fun createAddressCustomer(address: Address){
        addressCustomer.postValue(address)
    }
}