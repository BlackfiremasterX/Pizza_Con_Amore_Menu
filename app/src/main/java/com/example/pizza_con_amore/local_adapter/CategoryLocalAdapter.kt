package com.example.pizza_con_amore.local_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.R


import com.example.pizza_con_amore.local_data.CategoryLocalData

class CategoryLocalAdapter(recyclerListCategory: ArrayList<CategoryLocalData>,context: Context): RecyclerView.Adapter<CategoryHolder>(){
    var categoryList = recyclerListCategory
    var contexCategory = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
       val inflater = LayoutInflater.from(contexCategory).inflate(R.layout.menu_food_category_item,parent,false)
        return CategoryHolder(inflater)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position],contexCategory)
        val categoryListItem = categoryList[position]
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addCategory(category: CategoryLocalData )
    {
        categoryList.add(category)
        notifyDataSetChanged()
    }

    fun updateAdapter(itemList: List<CategoryLocalData>)
    {
        categoryList.clear()
        categoryList.addAll(itemList)
        notifyDataSetChanged()
    }
}