package com.example.householdappliances.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.householdappliances.R
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.navigation.KeyDataIntent.CATEGORY
import com.example.householdappliances.ui.screen.detail.DetailActivity
import com.example.householdappliances.ui.screen.home.viewpager.CategoryFragment
import com.example.householdappliances.ui.screen.login.CreateAccountFragment
import com.example.householdappliances.ui.screen.login.LoginActivity
import com.example.householdappliances.ui.screen.main.MainActivity
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

    fun gotoDetailActivityScreen(){
        val intent = Intent(context, DetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoCreateAccountFragmentScreen(parentFragmentManager: FragmentManager){
        val bundle = Bundle()
        val fragment = CreateAccountFragment().apply { arguments = bundle }
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame_login, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}