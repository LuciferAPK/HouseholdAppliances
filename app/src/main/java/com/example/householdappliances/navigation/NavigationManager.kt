package com.example.householdappliances.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.householdappliances.ui.screen.main.MainActivity
import javax.inject.Singleton

@Singleton
class NavigationManager(private val context: Context) {
    fun gotoMainActivityScreen(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}