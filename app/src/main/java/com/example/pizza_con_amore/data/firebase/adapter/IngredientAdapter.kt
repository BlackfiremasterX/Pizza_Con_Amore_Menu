package com.example.pizza_con_amore.data.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.MenuIngredientItemBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.*


class IngredientAdapter(private val ingredientList: ArrayList<IngredientsData>,context: Context) :
    RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {
    private var contextCompound = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val ingredientItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_ingredient_item, parent, false)
        return IngredientHolder(ingredientItemView)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val currentItem = ingredientList[position]
        holder.binding.ingredientText.text = currentItem.ingredientName + "\u0020"
        //holder.binding.ingredientMass.text = currentItem.ingredientMass +"грамм\u0020"

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