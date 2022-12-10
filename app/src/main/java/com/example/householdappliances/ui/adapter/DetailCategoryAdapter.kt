package com.example.householdappliances.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.householdappliances.R
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.LayoutItemByCategoryBinding

class DetailCategoryAdapter(
    private val context: Context,
    private val items: ArrayList<Item>,
    private val onClickItemCategoryListener: (Int, Item) -> Unit,
    private val onClickAddToCartListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.layout_item_by_category,
            parent,
            false
        ) as LayoutItemByCategoryBinding
        return ViewHolder(binding, onClickItemCategoryListener, onClickAddToCartListener)
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
        val binding: LayoutItemByCategoryBinding,
        private val onClickCategoryListener: (Int, Item) -> Unit,
        private val onClickAddToCartListener: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, item: Item) {
            binding.txtName.text = item.name
            binding.txtChina.text = "Xuất sứ: ${item.origin}"
            binding.txtPrice.text = "Giá: ${item.price}"
            Glide.with(context)
                .load(item.image)
                .into(binding.imgCategory)
            binding.root.setOnClickListener {
                onClickCategoryListener.invoke(position, item)
            }
            binding.imgBuyItem.setOnClickListener {
                onClickAddToCartListener.invoke()
            }
        }
    }
}