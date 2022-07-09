package com.example.pizza_con_amore.local_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.R


import com.example.pizza_con_amore.local_data.CategoryLocalData
import com.example.pizza_con_amore.local_data.FoodItemLocalData

class FoodItemLocalAdapter(recyclerListCategory: ArrayList<FoodItemLocalData>, context: Context): RecyclerView.Adapter<FoodItemHolder>(){
    var foodList = recyclerListCategory
    var contexFood = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder {
       val inflater = LayoutInflater.from(contexFood).inflate(R.layout.menu_food_item,parent,false)
        return FoodItemHolder(inflater)
    }

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
        holder.bind(foodList[position],contexFood)
        val foodListItem = foodList[position]
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun addFood(food: FoodItemLocalData)
    {
        foodList.add(food)
        notifyDataSetChanged()
    }

    fun updateAdapter(itemList: List<FoodItemLocalData>)
    {
        foodList.clear()
        foodList.addAll(itemList)
        notifyDataSetChanged()
    }
}