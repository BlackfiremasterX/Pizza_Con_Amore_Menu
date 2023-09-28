package com.example.pizza_con_amore.presentation.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.data.foodArrayList
import com.example.pizza_con_amore.data.getCategoryRef
import com.example.pizza_con_amore.data.livedata
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.data.firebase.adapter.FoodAdapter
import com.example.pizza_con_amore.presentation.ui.HomeFragment
import com.google.firebase.database.*


open class ActiveCategoryFragment : HomeFragment() {

    lateinit var foodRV: RecyclerView
    lateinit var food_adapter: FoodAdapter
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
            food_adapter = FoodAdapter(foodArrayList,this@ActiveCategoryFragment,context!!)
            onCategoryClick(CategoryData())


        }
        return root
    }


    private fun getFoodData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getCategoryRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    foodArrayList.clear()
                    if (snapshot.exists()) {
                        for (foodSnapsot in snapshot.children) {
                            try{
                                val food = foodSnapsot.getValue(FoodData::class.java)
                                foodArrayList.add(food!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(foodSnapsot.value)
                            }}
                        foodRV.adapter = FoodAdapter(foodArrayList,this@ActiveCategoryFragment, context!!)
                        //Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"Обновление в говне",Toast.LENGTH_SHORT).show()
                }
            }
            pca_base.addValueEventListener(getData)
            pca_base.addListenerForSingleValueEvent(getData)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryClick(category: CategoryData) {
        getFoodData()
        food_adapter.notifyDataSetChanged()
        foodRV = binding.mainFoodScroll
        super.onCategoryClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = ActiveCategoryFragment()
    }

}