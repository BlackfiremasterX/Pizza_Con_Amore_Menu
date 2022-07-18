package com.example.pizza_con_amore.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.IndividualHotDrinksFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.DrinksAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.*


open class HotDrinksFragment : HomeFragment() {

    lateinit var drinksRV: RecyclerView
    lateinit var drinks_adapter:DrinksAdapter
    private var _binding: IndividualHotDrinksFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = IndividualHotDrinksFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            drinksRV = coffeeScroller
            drinksRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            foodArrayList = arrayListOf<FoodData>()
            drinks_adapter = DrinksAdapter(foodArrayList,context!!)
            onClick(CategoryData())


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
                        drinksRV.adapter = FoodAdapter(foodArrayList, context!!)
                       // Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
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

    override fun onClick(category: CategoryData) {
        getFoodData()
        drinks_adapter.notifyDataSetChanged()
        drinksRV = binding.coffeeScroller
        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HotDrinksFragment()
    }

}