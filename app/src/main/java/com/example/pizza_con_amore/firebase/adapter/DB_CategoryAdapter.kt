package com.example.pizza_con_amore.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.ACTIVE_CATEGORY
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.MenuFoodCategoryItemBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure

class DB_CategoryAdapter(private val categoryList: ArrayList<FirebaseDataStructure.CategoryData>,
                         context: Context
) :
    RecyclerView.Adapter<DB_CategoryAdapter.CategoryHolder>() {
    private var contexCategory = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val categoryItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_food_category_item, parent, false)
        return CategoryHolder(categoryItemView)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.binding.categoryFoodText.text = currentItem.categoryTitle + "\u0020"
        holder.binding.categoryFoodBuffer.text = currentItem.categoryName

        //Подгрузка картинки элемента.
        Glide.with(holder.binding.categoryFoodImg.context)
            .load(currentItem.categoryImageLink)
            .placeholder(R.drawable.fimage_pizza)
            .error(R.drawable.favorite_star)
            .into(holder.binding.categoryFoodImg)

        holder.bind(categoryList[position], contexCategory)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class CategoryHolder(categoryItem: View) : RecyclerView.ViewHolder(categoryItem) {
        val binding = MenuFoodCategoryItemBinding.bind(categoryItem)
        fun bind(categoryItem: FirebaseDataStructure.CategoryData, context: Context) = with(binding)
        {
            foodCategoryItemCard.setOnClickListener() {
                val ac: String

                when (categoryFoodBuffer.text) {
                    "01_breakfast" -> ac = "01_breakfast"
                    "02_pizza" -> ac = "02_pizza"
                    "03_focaccia" -> ac = "03_focaccia"
                    "04_lunch" -> ac = "04_lunch"
                    "05_pasta" -> ac = "05_pasta"
                    "06_hot_snacks" -> ac = "06_hot_snacks"
                    "07_salad" -> ac = "07_salad"
                    "08_raviolli" -> ac = "08_raviolli"
                    "09_hot_meal" -> ac = "09_hot_meal"
                    "10_ice_cream" -> ac = "10_ice_cream"
                    "11_milkshakes" -> ac = "11_milkshakes"
                    "12_hot_drinks" -> ac = "12_hot_drinks"
                    "13_cold_drinks" -> ac = "13_cold_drinks"
                    else -> ac = "pizza"
                }
                ACTIVE_CATEGORY = ac
                Toast.makeText(context, ACTIVE_CATEGORY, Toast.LENGTH_SHORT).show()

            }
        }
    }
    }