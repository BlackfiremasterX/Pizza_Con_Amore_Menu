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
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.MenuFoodItemBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.FoodData
import com.example.pizza_con_amore.interfaces.FoodClickInterface
import com.example.pizza_con_amore.ui.FoodItemDetailsFragment
import com.example.pizza_con_amore.ui.HomeFragment
import com.example.pizza_con_amore.ui.category_fragments.HotDrinksFragment


class FoodAdapter(
    private val foodList: ArrayList<FoodData>,
    var clickListener: f_Listener,
    context: Context
) :
    RecyclerView.Adapter<FoodAdapter.FoodHolder>() {
    private var contextFood = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val foodItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_food_item, parent, false)
        return FoodHolder(foodItemView, clickListener)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val currentItem = foodList[position]
        //  holder.binding.foodItemTitle.text = currentItem.foodName + "\u0020"
        // holder.binding.foodItemMass.text ="\u0020" + currentItem.foodMass + "\u0020"
        //  holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"
        // holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"
        //holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"


        //Подгрузка картинки элемента.
        Glide.with(holder.binding.foodItemImg.context)
            .load(currentItem.foodImageLink)
            .placeholder(R.drawable.bg_pizza_steam)
            .error(R.drawable.bg_pizza_steam)
            .into(holder.binding.foodItemImg)

        holder.initialize(currentItem, clickListener)

        holder.bind(currentItem, contextFood)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    class FoodHolder(foodItem: View, listener: f_Listener) : RecyclerView.ViewHolder(foodItem) {
        val binding = MenuFoodItemBinding.bind(foodItem)

//        init {
//            binding.apply {
//
//                foodItemCard.setOnClickListener(){
//                    listener.onFoodItemClick(FoodData(),adapterPosition)
//
//
//
//                }
//
//            }
//        }

        fun initialize(item: FoodData, action: f_Listener) {
            binding.apply {
                foodItemTitle.text = item.foodName + "\u0020"
                foodItemMass.text = "\u0020" + item.foodMass + "\u0020"
                foodItemPrice.text = "\u0020" + item.foodPrice + "₽\u0020"

                //добавить

                Glide.with(binding.foodItemImg.context)
                    .load(item.foodImageLink)
                    .placeholder(R.drawable.bg_pizza_steam)
                    .error(R.drawable.bg_pizza_steam)
                    .into(binding.foodItemImg)

                itemView.setOnClickListener {
                    action.onFoodItemClick(item, bindingAdapterPosition)
                    ACTIVE_FOOD = item.foodId.toString()

                    ACTIVE_FOOD_TITLE = "\u0020"+ item.foodName + "\u0020"
                    ACTIVE_FOOD_PRICE = "\u0020" + item.foodPrice + "₽\u0020"
                    ACTIVE_FOOD_MASS = "\u0020" + item.foodMass + "\u0020"
                    ACTIVE_FOOD_DESCRIPTION = item.foodDescription + "\u0020"
                    ACTIVE_FOOD_IMAGE_LINK = item.foodImageLink.toString()


                }
            }
        }

        fun bind(foodItem: FoodData, context: Context) = with(binding)
        {

        }
    }

    interface f_Listener {
        fun onFoodItemClick(food: FoodData, position: Int)
    }
}