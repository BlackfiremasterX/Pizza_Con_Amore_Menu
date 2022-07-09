package com.example.pizza_con_amore.local_adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.databinding.MenuFoodCategoryItemBinding
import com.example.pizza_con_amore.databinding.MenuFoodItemBinding
import com.example.pizza_con_amore.local_data.CategoryLocalData
import com.example.pizza_con_amore.local_data.FoodItemLocalData
import com.example.pizza_con_amore.ui.FoodItemDetailsFragment


class FoodItemHolder(item: View): RecyclerView.ViewHolder(item) {
    val binding = MenuFoodItemBinding.bind(item)
    fun bind(foodItem: FoodItemLocalData,context: Context) = with(binding)
    {
        foodItemImg.setImageResource(foodItem.imageId)
        foodItemTitle.text = foodItem.title
        foodItemPrice.text = foodItem.price.toString()
        foodItemMass.text = foodItem.mass.toString()
        foodItemCard.setOnClickListener(){
            val intent_to_food_details = Intent(context,FoodItemDetailsFragment::class.java).apply {
                putExtra("image",foodItem.imageId)
                putExtra("title",foodItemTitle.text.toString())
                putExtra("price",foodItemPrice.text.toString())
                putExtra("mass",foodItemMass.text.toString())
            }
            context.startActivity(intent_to_food_details)
        }
    }
}
