package com.example.pizza_con_amore.data.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.ComplexItemBinding
import com.example.pizza_con_amore.databinding.HotDrinksItemBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.FoodData


class LunchComplexAdapter(private val complexList: ArrayList<FoodData>, context: Context) :
    RecyclerView.Adapter<LunchComplexAdapter.LunchComplexHolder>() {
    private var contextComplex = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LunchComplexHolder {
        val complexItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.complex_item, parent, false)
        return LunchComplexHolder(complexItemView)
    }

    override fun onBindViewHolder(holder: LunchComplexHolder, position: Int) {
        val currentItem = complexList[position]
        holder.binding.complexItemTitle.text = currentItem.foodName + "\u0020"
        holder.binding.complexItemMass.text ="\u0020" + currentItem.foodMass + "\u0020"
        holder.binding.complexItemPrice.text = "\u0020"+ currentItem.foodPrice + " ₽ \u0020"

        holder.bind(currentItem,contextComplex)
    }

    override fun getItemCount(): Int {
        return complexList.size
    }


    class LunchComplexHolder(complexItem: View): RecyclerView.ViewHolder(complexItem) {
        val binding = ComplexItemBinding.bind(complexItem)
        fun bind(complexItem: FoodData, context: Context) = with(binding)
        {
            complexItemCard.setOnClickListener(){
                Toast.makeText(context,"Нажато: ${complexItemTitle.text}", Toast.LENGTH_SHORT).show()
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