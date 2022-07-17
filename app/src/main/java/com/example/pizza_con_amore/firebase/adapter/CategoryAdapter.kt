package com.example.pizza_con_amore.firebase.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.ACTIVE_CATEGORY
import com.example.pizza_con_amore.NODE_CATEGORIES
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.categoryArrayList
import com.example.pizza_con_amore.databinding.MenuFoodCategoryItemBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.google.firebase.database.FirebaseDatabase


class CategoryAdapter(
    val listener: c_Listener,
    private val categoryList: ArrayList<CategoryData>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

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

        holder.bind(categoryList[position], listener)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


    class CategoryHolder(categoryItem: View) : RecyclerView.ViewHolder(categoryItem) {
        val binding = MenuFoodCategoryItemBinding.bind(categoryItem)
        fun bind(categoryItem: CategoryData, listener: c_Listener) = with(binding)
        {
            foodCategoryItemCard.setOnClickListener() {
                listener.onClick(categoryItem)
            }
        }
    }

    interface c_Listener {
        fun onClick(category: CategoryData)
    }
}