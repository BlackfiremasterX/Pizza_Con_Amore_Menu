package com.example.pizza_con_amore.local_adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.databinding.MenuFoodCategoryItemBinding
import com.example.pizza_con_amore.local_data.CategoryLocalData

class CategoryHolder(item: View): RecyclerView.ViewHolder(item) {
    val binding = MenuFoodCategoryItemBinding.bind(item)
    fun bind(categoryItem: CategoryLocalData,context: Context) = with(binding)
    {
        categoryFoodImg.setImageResource(categoryItem.imageId)
        categoryFoodText.text = categoryItem.title
        foodCategoryItemCard.setOnClickListener(){
            Toast.makeText(context,"Нажато: ${categoryFoodText.text}",Toast.LENGTH_SHORT).show()
        }
    }
}
