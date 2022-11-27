package com.example.householdappliances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.householdappliances.R
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.databinding.LayoutItemBinding

class CategoryAdapter(
    private val context: Context,
    private val items: ArrayList<Category>,
    private val onClickCategoryListener: (Int, Category) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.layout_item,
            parent,
            false
        ) as LayoutItemBinding
        return ViewHolder(binding, onClickCategoryListener)
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

    inner class ViewHolder(val binding: LayoutItemBinding, private val onClickCategoryListener: (Int, Category) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, category: Category) {
            binding.txtCategory.text = category.name
            Glide.with(context)
                .load(category.image)
                .into(binding.imgCategory)
            binding.root.setOnClickListener {
                onClickCategoryListener.invoke(position, category)
            }
        }
    }
}