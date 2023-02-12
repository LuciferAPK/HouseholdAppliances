package com.example.householdappliances.dialog

import android.os.Bundle
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseDialog
import com.example.householdappliances.databinding.LayoutDialogClearCartBinding

class DialogClearCart(private val onClickDelete: () -> Unit) : BaseDialog<LayoutDialogClearCartBinding>() {
    override fun getLayoutResource(): Int {
        return R.layout.layout_dialog_clear_cart
    }

    override fun init(saveInstanceState: Bundle?, view: View?) {
        binding.btnYes.setOnClickListener {
            onClickDelete.invoke()
            dismiss()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    override fun setUp(view: View?) {

    }

    override fun getGravityForDialog(): Int {
        return 0
    }
}