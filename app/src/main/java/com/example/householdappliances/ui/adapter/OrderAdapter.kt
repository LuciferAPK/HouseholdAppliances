package com.example.householdappliances.ui.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.householdappliances.R
import com.example.householdappliances.base.BaseRecyclerAdapter
import com.example.householdappliances.data.model.Order
import com.example.householdappliances.databinding.ItemOrderBinding
import com.example.householdappliances.ui.screen.viewholder.ViewHolderLifeCircle

class OrderAdapter(dataSet : MutableList<Order?>) : BaseRecyclerAdapter<Order, OrderAdapter.OrderViewHolder>(dataSet) {

    override fun getLayoutResourceItem(): Int {
        return R.layout.item_order
    }

    override fun setViewHolderLifeCircle(): ViewHolderLifeCircle? {
        return null
    }

    override fun onCreateBasicViewHolder(binding: ViewDataBinding?): OrderViewHolder {
        return OrderViewHolder(binding as ItemOrderBinding)
    }

    override fun onBindBasicItemView(holder: OrderViewHolder, position: Int) {
        holder.bind(getDataSet()?.get(position))
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data: Order?) {
            binding.tvStatus.text = "Trạng thái: ${data?.status}"
            binding.thanhToan.text = "Hình thức thanh toán: ${data?.payment}"
            binding.txtMoney.text = "Tổng thanh toán: ${data?.finalPrice.toString()}"
        }
    }
}