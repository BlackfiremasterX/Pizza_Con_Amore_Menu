package com.example.pizza_con_amore.firebase.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.MainActivity
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.FragmentFoodItemDetailsBinding
import com.example.pizza_con_amore.databinding.MenuFoodCategoryItemBinding
import com.example.pizza_con_amore.databinding.MenuIngredientItemBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*


class IngredientAdapter(private val ingredientList: ArrayList<IngredientsData>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val categoryItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_food_category_item, parent, false)
        return IngredientHolder(categoryItemView)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val currentItem = ingredientList[position]
        holder.binding.ingredientText.text = currentItem.ingredientName + "\u0020"
        holder.binding.ingredientMass.text = currentItem.ingredientMass +"грамм\u0020"

        //Подгрузка картинки элемента.
        Glide.with(holder.binding.ingredientImg.context)
            .load(currentItem.ingredientImageLink)
            .placeholder(R.drawable.fimage_pizza)
            .error(R.drawable.favorite_star)
            .into(holder.binding.ingredientImg)

        holder.bind(ingredientList[position])
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }


    class IngredientHolder(ingredientItem: View) : RecyclerView.ViewHolder(ingredientItem) {
        val binding = MenuIngredientItemBinding.bind(ingredientItem)
        fun bind(ingredientItem: IngredientsData, ) = with(binding)
        {
            ingredientItemCard.setOnClickListener() {

               //Toast.makeText(FragmentFoodItemDetailsBinding,"Ингредиент",Toast.LENGTH_SHORT).show()
            }
        }
    }

}