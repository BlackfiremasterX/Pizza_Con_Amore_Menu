package com.example.pizza_con_amore.firebase.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.firebase.FirebaseDataStructure


class DB_CategoryAdapter(private val categoryList: ArrayList<FirebaseDataStructure.CategoryData_fromDB>) :
    RecyclerView.Adapter<DB_CategoryAdapter.DB_ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DB_ViewHolder {
        val categoryItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_food_category_item, parent, false)
        return DB_ViewHolder(categoryItemView)
    }

    override fun onBindViewHolder(holder: DB_ViewHolder, position: Int) {
        val currentItem = categoryList[position]
        val url = currentItem.categoryImageLink.toString()
        holder.categoryTitle.text = currentItem.categoryTitle
        Glide.with(holder.categoryImage.context)
            .load(currentItem.categoryImageLink)
            .placeholder(R.drawable.fimage_pizza)
            .error(R.drawable.favorite_star)
            .into(holder.categoryImage)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class DB_ViewHolder(categoryItemView: View) : RecyclerView.ViewHolder(categoryItemView) {
        val categoryTitle: TextView = categoryItemView.findViewById<TextView>(R.id.category_food_text)
        val categoryImage: ImageView = categoryItemView.findViewById<ImageView>(R.id.category_food_img)

    }




}