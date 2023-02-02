package com.example.householdappliances.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.householdappliances.R
import com.example.householdappliances.data.model.ItemSpinner
import com.example.householdappliances.databinding.LayoutItemCrudBinding

class CRUDSpinnerAdapter(private val dataSet: MutableList<ItemSpinner>) : BaseAdapter() {
    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        return initView(p0,p1,p2)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return initView(position, convertView, parent)
    }

    fun initView(position: Int, view: View?, parent: ViewGroup?): View{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding =DataBindingUtil.inflate(layoutInflater,
            R.layout.layout_item_crud, parent, false) as LayoutItemCrudBinding
        binding.tvNameFunction.text = dataSet[position].title
        dataSet[position].imageCRUD?.let { binding.imgCrud.setImageResource(it) }
        dataSet[position].icon?.let { binding.icon.setImageResource(it) }
        return binding.root
    }
}