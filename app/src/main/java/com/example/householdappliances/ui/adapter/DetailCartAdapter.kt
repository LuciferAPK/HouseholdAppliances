package com.example.householdappliances.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.householdappliances.R
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.CartItem
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.LayoutItemByCardBinding
import com.example.householdappliances.databinding.LayoutItemByCategoryBinding

class DetailCartAdapter(
    private val context: Context,
    private val items: ArrayList<CartItem?>,
    private val onClickDeleteItemListener: (Int, Item?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.layout_item_by_card,
            parent,
            false
        ) as LayoutItemByCardBinding
        return ViewHolder(binding, onClickDeleteItemListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            if (holder is ViewHolder) {
                 holder.bind(position, items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(
        val binding: LayoutItemByCardBinding,
        private val onClickDeleteItemListener: (Int, Item?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, item: CartItem?) {
            binding.txtName.text = item?.item?.name
            binding.txtChina.text = "Xuất sứ: ${item?.item?.origin}"
            binding.txtPrice.text = "Giá: ${item?.item?.price}VND"
            binding.tvAmount.text = "Số lượng: ${item?.amount}"
            Glide.with(context)
                .load(item?.item?.image)
                .into(binding.imgCategory)
            binding.imgDelete.setOnClickListener {
                onClickDeleteItemListener.invoke(position, item?.item)
            }
        }
    }
}