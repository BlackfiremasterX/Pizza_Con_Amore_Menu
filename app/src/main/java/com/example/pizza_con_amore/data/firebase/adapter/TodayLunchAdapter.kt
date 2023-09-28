package com.example.pizza_con_amore.data.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.TodayLunchItemBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.FoodData


class TodayLunchAdapter(private val todayLunchList: ArrayList<FoodData>, context: Context) :
    RecyclerView.Adapter<TodayLunchAdapter.TodayLunchHolder>() {
    private var contextTodayLunch = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayLunchHolder {
        val todayLunchItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.today_lunch_item, parent, false)
        return TodayLunchHolder(todayLunchItemView)
    }

    override fun onBindViewHolder(holder: TodayLunchHolder, position: Int) {
        val currentItem = todayLunchList[position]
        holder.binding.todayLunchItemTitle.text = currentItem.foodName + "\u0020"
        // holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"
        //holder.binding.foodItemPrice.text = "\u0020"+ currentItem.foodPrice + "₽\u0020"


        //Подгрузка картинки элемента.
        Glide.with(holder.binding.todayLunchItemImg.context)
            .load(currentItem.foodImageLink)
            .placeholder(R.drawable.bg_pizza_steam)
            .error(R.drawable.bg_pizza_steam)
            .into(holder.binding.todayLunchItemImg)

        holder.bind(currentItem, contextTodayLunch)
    }

    override fun getItemCount(): Int {
        return todayLunchList.size
    }


    class TodayLunchHolder(todayLunchItem: View) : RecyclerView.ViewHolder(todayLunchItem) {
        val binding = TodayLunchItemBinding.bind(todayLunchItem)
        fun bind(foodItem: FoodData, context: Context) = with(binding)
        {val toast = Toast.makeText(context, todayLunchItemTitle.text, Toast.LENGTH_LONG)
            todayLunchItemCard.setOnClickListener() {

                toast.cancel()
                toast.show()
            }


        }
    }
}