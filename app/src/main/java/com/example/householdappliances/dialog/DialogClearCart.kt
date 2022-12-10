package com.example.householdappliances.dialog

import android.os.Bundle
import android.view.View
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseDialog
import com.example.householdappliances.databinding.LayoutDialogClearCartBinding

class DialogClearCart : BaseDialog<LayoutDialogClearCartBinding>() {
    override fun getLayoutResource(): Int {
        return R.layout.layout_dialog_clear_cart
    }

    override fun init(saveInstanceState: Bundle?, view: View?) {
        binding.btnYes.setOnClickListener {
            dismiss()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    override fun setUp(view: View?) {

    }
}