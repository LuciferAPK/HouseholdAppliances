package com.example.householdappliances.ui.screen.cart

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.householdappliances.base.Result
import com.example.householdappliances.base.SingleLiveEvent
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.Order
import com.example.householdappliances.network.END_POINT_ADD_CART_ITEM_TO_CART
import com.example.householdappliances.network.END_POINT_CREATE_ORDER
import com.example.householdappliances.network.END_POINT_GET_CART_OF_CUSTOMER
import com.example.householdappliances.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    val getCartByIdCustomer = SingleLiveEvent<Result<Cart>>()
    fun getCartOfCustomer(
        url: String? = END_POINT_GET_CART_OF_CUSTOMER
    ) {
        val request = cartRepository.getCartOfCustomer(
            url = url
        )
        getCartByIdCustomer.addSource(request) {
            getCartByIdCustomer.postValue(it)
        }
    }

    val updateCartResult = SingleLiveEvent<Result<Cart>>()
    fun addCartItemToCart(
        url: String? = END_POINT_ADD_CART_ITEM_TO_CART,
        cart: Cart?
    ) {
        val request = cartRepository.addCartItemToCart(
            url = url,
            cart = cart
        )
        updateCartResult.addSource(request) {
            updateCartResult.postValue(it)
        }
    }

    val takeOrderResult = SingleLiveEvent<com.example.householdappliances.base.Result<Order>>()
    fun takeOrder(
        url: String? = END_POINT_CREATE_ORDER,
        order: Order?
    ){
        val request = cartRepository.takeOrder(url, order)
        takeOrderResult.addSource(request){
            takeOrderResult.postValue(it)
        }
    }
}