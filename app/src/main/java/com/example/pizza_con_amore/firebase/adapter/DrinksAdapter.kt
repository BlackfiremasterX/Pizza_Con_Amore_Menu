package com.example.pizza_con_amore.firebase.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.MainActivity
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.MenuFoodItemBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.FoodData
import com.example.pizza_con_amore.ui.FoodItemDetailsFragment


class DrinksAdapter(private val foodList: ArrayList<FoodData>, context: Context) :
    RecyclerView.Adapter<DrinksAdapter.DrinksHolder>() {
    private var contextFood = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
        val foodItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.drinks_item, parent, false)
        return DrinksHolder(foodItemView)
    }

    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
        val currentItem = foodList[position]
        holder.binding.foodItemTitle.text = currentItem.foodName + "\u0020"
        holder.binding.foodItemMass.text ="\u0020" + currentItem.foodMass + "\u0020"
        holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"
       // holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"
        //holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"


        //Подгрузка картинки элемента.
        Glide.with(holder.binding.foodItemImg.context)
            .load(currentItem.foodImageLink)
            .placeholder(R.drawable.bg_pizza_steam)
            .error(R.drawable.bg_pizza_steam)
            .into(holder.binding.foodItemImg)

        holder.bind(currentItem,contextFood)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    class DrinksHolder(foodItem: View): RecyclerView.ViewHolder(foodItem) {
        val binding = MenuFoodItemBinding.bind(foodItem)
        fun bind(foodItem: FoodData, context: Context) = with(binding)
        {
            foodItemCard.setOnClickListener(){
                Toast.makeText(context,"Нажато: ${foodItemTitle.text}", Toast.LENGTH_SHORT).show()
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