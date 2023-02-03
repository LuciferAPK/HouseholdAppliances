package com.example.householdappliances.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.householdappliances.R
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.navigation.KeyDataIntent.CART
import com.example.householdappliances.navigation.KeyDataIntent.CATEGORY
import com.example.householdappliances.navigation.KeyDataIntent.ITEM
import com.example.householdappliances.ui.screen.account.AddressFragment
import com.example.householdappliances.ui.screen.cart.CartActivity
import com.example.householdappliances.ui.screen.detail.DetailActivity
import com.example.householdappliances.ui.screen.home.viewpager.CategoryFragment
import com.example.householdappliances.ui.screen.login.CreateAccountFragment
import com.example.householdappliances.ui.screen.login.LoginActivity
import com.example.householdappliances.ui.screen.main.MainActivity
import com.example.householdappliances.ui.screen.order.OrderActivity
import javax.inject.Singleton

@Singleton
class NavigationManager(private val context: Context) {
    fun gotoMainActivityScreen(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoLoginActivityScreen(){
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoCategoryFragmentScreen(parentFragmentManager: FragmentManager, category: Category) {
        val bundle = Bundle()
        bundle.putSerializable(CATEGORY, category)
        val fragment = CategoryFragment().apply { arguments = bundle }
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame_for_you, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }

    fun gotoDetailActivityScreen(item: Item){
        val bundle = Bundle()
        bundle.putSerializable(ITEM, item)
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoCartActivityScreen(activity: Activity, cart: Cart?= null){
        val intent = Intent(context, CartActivity::class.java)
        if(cart != null){
            val bundle = Bundle()
            bundle.putSerializable(CART, cart)
            intent.putExtras(bundle)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

    fun gotoOrderActivityScreen(activity: Activity, cart: Cart?= null){
        val intent = Intent(context, OrderActivity::class.java)
        if(cart != null){
            val bundle = Bundle()
            bundle.putSerializable(CART, cart)
            intent.putExtras(bundle)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

    fun gotoCreateAccountFragmentScreen(parentFragmentManager: FragmentManager){
        val bundle = Bundle()
        val fragment = CreateAccountFragment().apply { arguments = bundle }
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame_login, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }

    fun gotoAddressFragmentScreen(parentFragmentManager: FragmentManager){
        val bundle = Bundle()
        val fragment = AddressFragment().apply { arguments = bundle }
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}