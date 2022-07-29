package com.example.pizza_con_amore.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.ColdDrinksItemBinding

import com.example.pizza_con_amore.firebase.FirebaseDataStructure.FoodData


class ColdDrinksAdapter(private val drinksList: ArrayList<FoodData>, context: Context) :
    RecyclerView.Adapter<ColdDrinksAdapter.ColdDrinksHolder>() {
    private var contextDrink = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColdDrinksHolder {
        val drinkItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cold_drinks_item, parent, false)
        return ColdDrinksHolder(drinkItemView)
    }

    override fun onBindViewHolder(holder: ColdDrinksHolder, position: Int) {
        val currentItem = drinksList[position]
        holder.binding.coldDrinksItemTitle.text = currentItem.foodName + "\u0020"
        holder.binding.coldDrinksItemMass.text ="\u0020" + currentItem.foodMass + "\u0020"
        holder.binding.coldDrinksItemPrice.text = "\u0020"+ currentItem.foodPrice + " ₽ \u0020"

        //Подгрузка картинки элемента.
        Glide.with(holder.binding.coldDrinksItemImg.context)
            .load(currentItem.foodImageLink)
            .placeholder(R.drawable.bg_pizza_steam)
            .error(R.drawable.bg_pizza_steam)
            .into(holder.binding.coldDrinksItemImg)

        holder.bind(currentItem,contextDrink)
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }


    class ColdDrinksHolder(drinksItem: View): RecyclerView.ViewHolder(drinksItem) {
        val binding = ColdDrinksItemBinding.bind(drinksItem)
        fun bind(drinkItem: FoodData, context: Context) = with(binding)
        {
            coldDrinksItemCard.setOnClickListener(){
                Toast.makeText(context,"Нажато: ${coldDrinksItemTitle.text}", Toast.LENGTH_SHORT).show()
               // val intent_to_food_details = Intent(context, FoodItemDetailsFragment::class.java).apply {
//                    putExtra("title",foodItemTitle.text.toString())
//                    putExtra("price",foodItemPrice.text.toString())
//                    putExtra("mass",foodItemMass.text.toString())
//                    putExtra("mass",foodItemMass.text.toString())
//                    putExtra("mass",foodItemMass.text.toString())
//                    putExtra("mass",foodItemMass.text.toString())
                }
               // context.startActivity(intent_to_food_details)
            }

        }
}