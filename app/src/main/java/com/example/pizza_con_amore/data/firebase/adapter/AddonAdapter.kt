package com.example.pizza_con_amore.data.firebase.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.BreakfastAddonItemBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.*

class AddonAdapter(private val addonList: ArrayList<AddonsData>, context: Context) :
    RecyclerView.Adapter<AddonAdapter.AddonHolder>() {
    private var contextAddon = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddonHolder {
        val addonItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.breakfast_addon_item, parent, false)
        return AddonHolder(addonItemView)
    }

    override fun onBindViewHolder(holder: AddonHolder, position: Int) {
        val currentItem = addonList[position]
        holder.binding.addonItemTitle.text = currentItem.addonName + "\u0020"
        holder.binding.addonItemMass.text = currentItem.addonMass +"гр.\u0020"
        holder.binding.addonItemPrice.text = currentItem.addonPrice +"₽\u0020"

        //Подгрузка картинки элемента.
        Glide.with(holder.binding.addonItemImg.context)
            .load(currentItem.addonImageLink)
            .placeholder(R.drawable.fimage_pizza)
            .error(R.drawable.favorite_star)
            .into(holder.binding.addonItemImg)

        holder.bind(addonList[position])
    }

    override fun getItemCount(): Int {
        return addonList.size
    }

    class AddonHolder(addonItem: View) : RecyclerView.ViewHolder(addonItem) {
        val binding = BreakfastAddonItemBinding.bind(addonItem)
        fun bind(ingredientItem: AddonsData, ) = with(binding)
        {
            addonItemImg.setOnClickListener() {
               //Toast.makeText(FragmentFoodItemDetailsBinding,"Ингредиент",Toast.LENGTH_SHORT).show()
            }
        }
    }

}