package com.example.pizza_con_amore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.ACTIVE_CATEGORY
import com.example.pizza_con_amore.NODE_CATEGORIES
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.adapter.DB_CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.DB_FoodAdapter
import com.example.pizza_con_amore.foodArrayList
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
            foodArrayList = arrayListOf<FirebaseDataStructure.FoodData>()
            getFoodData(ACTIVE_CATEGORY)
        }
        return root
    }
    private fun getFoodData(category: String) {
        pca_base = FirebaseDatabase.getInstance().getReference("$NODE_CATEGORIES/$category/${category}_list")
        pca_base.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodArrayList.clear()
                if (snapshot.exists()) {
                    for (foodSnapsot in snapshot.children) {
                        val food = foodSnapsot.getValue(FirebaseDataStructure.FoodData::class.java)
                        food?.let { foodArrayList.add(it) }
                    }
                    foodRV.adapter = activity?.let { DB_FoodAdapter(foodArrayList, it) }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ActiveCategoryFragment()
    }

}