package com.example.pizza_con_amore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
import com.google.firebase.database.*


open class ActiveCategoryFragment : HomeFragment() {

    lateinit var foodRV: RecyclerView
    private var _binding: FragmentActiveCategoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentActiveCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            foodRV = mainFoodScroll
            foodRV.layoutManager = GridLayoutManager(context, 2)
            foodArrayList = arrayListOf<FoodData>()
            onClick(CategoryData())
        }
        return root
    }


    private fun getFoodData() {


        pca_base = categories_dynamic_ref
        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodArrayList.clear()
                if (snapshot.exists()) {
                    for (foodSnapsot in snapshot.children) {
                        val food = foodSnapsot.getValue(FoodData::class.java)
                        foodArrayList.add(food!!) //food?.let { foodArrayList.add(it) }
                    }
                    foodRV.adapter = FoodAdapter(foodArrayList, context!!)
                Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"Обновление в говне",Toast.LENGTH_SHORT).show()

            }
        }
        pca_base.addValueEventListener(getData)
        pca_base.addListenerForSingleValueEvent(getData)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(category: CategoryData) {
        getFoodData()

        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = ActiveCategoryFragment()
    }

}