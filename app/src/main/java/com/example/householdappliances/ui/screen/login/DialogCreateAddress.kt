package com.example.householdappliances.ui.screen.login

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseDialog
import com.example.householdappliances.data.model.Address
import com.example.householdappliances.databinding.LayoutDialogCreateAddressBinding
import com.example.householdappliances.ui.screen.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogCreateAddress : BaseDialog<LayoutDialogCreateAddressBinding>() {
    private val viewModel: MainViewModel by activityViewModels()
    override fun getLayoutResource(): Int {
        return R.layout.layout_dialog_create_address
    }

    override fun init(saveInstanceState: Bundle?, view: View?) {

    }

    override fun setUp(view: View?) {
        binding.btnThem.setOnClickListener {
            var address = Address(
                city = binding.edtCity.text.toString().trim(),
                number = binding.edtNumber.text.toString().trim(),
                district = binding.edtDistrict.text.toString().trim(),
                ward = binding.edtWard.text.toString().trim(),
                street = binding.edtStreet.text.toString().trim()
            )
            viewModel.createAddressCustomer(address)
            this.dismiss()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun getGravityForDialog(): Int {
        return Gravity.BOTTOM
    }

    override fun onStart() {
        super.onStart()
        setSizeFullForDialog()
    }
}