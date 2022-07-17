package com.example.pizza_con_amore.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.FragmentFoodItemDetailsBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.IngredientsData
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.IngredientAdapter
import com.google.firebase.database.*

class FoodItemDetailsFragment : Fragment() {
    private var _binding: FragmentFoodItemDetailsBinding? = null
    lateinit var pca_base: DatabaseReference
    lateinit var ingredientRV: RecyclerView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodItemDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {

            ingredientRV = ingredientList
            ingredientRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            ingredientArrayList = arrayListOf<IngredientsData>()
            getIngredientData()
        }
        return root
    }

    private fun getIngredientData() {
        pca_base = FirebaseDatabase.getInstance().getReference(NODE_CATEGORIES).child(
            ACTIVE_CATEGORY).child(ACTIVE_CATEGORY+"_list").child(CURRENT_FOOD_ITEM).child("foodIngredientList")
        pca_base.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ingredientArrayList.clear()
                if (snapshot.exists()) {
                    for (ingredientSnapsot in snapshot.children) {
                        val ingredient = ingredientSnapsot.getValue(IngredientsData::class.java)
                        ingredientArrayList.add(ingredient!!)
                    }
                    ingredientRV.adapter = IngredientAdapter(ingredientArrayList)
                    // categoryRV.adapter = activity?.let { CategoryAdapter(categoryArrayList, it) }
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

    companion object{
        @JvmStatic
        fun newInstance() = FoodItemDetailsFragment()
    }
}