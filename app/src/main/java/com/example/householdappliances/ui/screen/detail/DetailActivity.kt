package com.example.householdappliances.ui.screen.detail

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseActivity
import com.example.householdappliances.databinding.ActivityDetailBinding
import com.example.householdappliances.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.view.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.activity_detail
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.txtTitle.text = "Thông tin sản phẩm"
    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.imgBuy.setOnClickListener {
            /**check login*/
            navigationManager.gotoLoginActivityScreen()
        }
        binding.addToCart.setOnClickListener {
            /**check login*/
            Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
        }
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}