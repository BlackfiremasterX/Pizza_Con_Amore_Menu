package com.example.pizza_con_amore.data.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.HotDrinksItemBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.FoodData


class HotDrinksAdapter(private val drinksList: ArrayList<FoodData>, context: Context) :
    RecyclerView.Adapter<HotDrinksAdapter.DrinksHolder>() {
    private var contextDrink = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
        val drinkItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hot_drinks_item, parent, false)
        return DrinksHolder(drinkItemView)
    }

    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
        val currentItem = drinksList[position]
        holder.binding.drinkItemTitle.text = currentItem.foodName + "\u0020"
        holder.binding.drinkItemMass.text ="\u0020" + currentItem.foodMass + "\u0020"
        holder.binding.drinkItemPrice.text = "\u0020"+ currentItem.foodPrice + " ₽ \u0020"

        holder.bind(currentItem,contextDrink)
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }


    class DrinksHolder(drinksItem: View): RecyclerView.ViewHolder(drinksItem) {
        val binding = HotDrinksItemBinding.bind(drinksItem)
        fun bind(drinkItem: FoodData, context: Context) = with(binding)
        {
            drinkItemCard.setOnClickListener(){
                Toast.makeText(context,"Нажато: ${drinkItemTitle.text}", Toast.LENGTH_SHORT).show()
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