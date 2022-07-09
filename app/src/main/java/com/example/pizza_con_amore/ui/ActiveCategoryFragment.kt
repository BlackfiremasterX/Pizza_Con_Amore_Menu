package com.example.pizza_con_amore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.local_adapter.FoodItemLocalAdapter
import com.example.pizza_con_amore.local_data.FoodItemLocalData


class ActiveCategoryFragment : HomeFragment() {

    private var _binding: FragmentActiveCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentActiveCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var foodFullParameterList = ArrayList<FoodItemLocalData>()
        foodFullParameterList.addAll(fillDataInFoodItems(
            getImageIdFromResources(R.array.pizza_img),
            resources.getStringArray(R.array.pizza_name),
            resources.getStringArray(R.array.pizza_price),
            resources.getStringArray(R.array.pizza_mass)))


                binding.apply {
                    menuHolder.layoutManager = GridLayoutManager(context, 2)
                    menuItemAdapter = context?.let { FoodItemLocalAdapter(foodFullParameterList, it) }
                    menuHolder.adapter = menuItemAdapter
                }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = ActiveCategoryFragment()
    }
}