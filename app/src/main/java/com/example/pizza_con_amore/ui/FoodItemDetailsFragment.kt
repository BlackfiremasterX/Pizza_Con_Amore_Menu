package com.example.pizza_con_amore.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.FragmentFoodItemDetailsBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.IngredientsData
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
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
            ingredientRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            compoundArrayList = arrayListOf<IngredientsData>()
            getIngredientData()
            activeFoodItemTitle.text = ACTIVE_FOOD_TITLE
            activeFoodItemMass.text = ACTIVE_FOOD_MASS
            activeFoodItemPrice.text = ACTIVE_FOOD_PRICE
            activeFoodDescriptionContent.text = ACTIVE_FOOD_DESCRIPTION

            Glide.with(binding.foodItemImg.context)
                .load(ACTIVE_FOOD_IMAGE_LINK)
                .placeholder(R.drawable.bg_pizza_steam)
                .error(R.drawable.bg_pizza_steam)
                .into(binding.foodItemImg)




        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {




            buttonBack.setOnClickListener { getFragmentManager()?.popBackStack() }



        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun getIngredientData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getCompoundRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    compoundArrayList.clear()
                    if (snapshot.exists()) {
                        for (ingredientSnapsot in snapshot.children) {
                            try{
                                val food = ingredientSnapsot.getValue(FirebaseDataStructure.IngredientsData::class.java)
                                compoundArrayList.add(food!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(ingredientSnapsot.value)
                            }}
                        ingredientRV.adapter = IngredientAdapter(compoundArrayList, context!!)
                        //Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"Обновление в говне", Toast.LENGTH_SHORT).show()
                }
            }
            pca_base.addValueEventListener(getData)
            pca_base.addListenerForSingleValueEvent(getData)
        }}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = FoodItemDetailsFragment()
    }
}