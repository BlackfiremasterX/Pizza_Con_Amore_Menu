package com.example.pizza_con_amore.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.IndividualHotDrinksFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.HotDrinksAdapter
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.*


open class HotDrinksFragment : HomeFragment() {

    lateinit var cofeeRV: RecyclerView
    lateinit var teaRV: RecyclerView
    lateinit var coffee_adapter: HotDrinksAdapter
    lateinit var tea_adapter: HotDrinksAdapter
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
            teaRV = teaScroller
            cofeeRV = coffeeScroller

            teaRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            cofeeRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            teaArrayList = arrayListOf<FoodData>()
            coffeeArrayList = arrayListOf<FoodData>()
            tea_adapter = HotDrinksAdapter(teaArrayList, context!!)
            coffee_adapter = HotDrinksAdapter(coffeeArrayList, context!!)
            onClick(CategoryData())
            getTeaData()
            getCoffeeData()
        }
        return root
    }

    private fun getCoffeeData() {
        livedata.observe(viewLifecycleOwner) {
            pca_base = getCoffeeRef(COFFEE)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    coffeeArrayList.clear()
                    if (snapshot.exists()) {
                        for (coffeeSnapsot in snapshot.children) {
                            try {
                                val coffee = coffeeSnapsot.getValue(FoodData::class.java)
                                coffeeArrayList.add(coffee!!) //food?.let { foodArrayList.add(it) }
                            } catch (e: Exception) {
                                println(e.message)
                                println(coffeeSnapsot.value)
                            }
                        }
                        cofeeRV.adapter = coffee_adapter
                        // Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Обновление в говне", Toast.LENGTH_SHORT).show()
                }
            }
            pca_base.addValueEventListener(getData)
            pca_base.addListenerForSingleValueEvent(getData)
        }
    }

    private fun getTeaData() {
        livedata.observe(viewLifecycleOwner) {
            pca_base = getTeaRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    teaArrayList.clear()
                    if (snapshot.exists()) {
                        for (coffeeSnapsot in snapshot.children) {
                            try {
                                val tea = coffeeSnapsot.getValue(FoodData::class.java)
                                teaArrayList.add(tea!!) //food?.let { foodArrayList.add(it) }
                            } catch (e: Exception) {
                                println(e.message)
                                println(coffeeSnapsot.value)
                            }
                        }
                        teaRV.adapter = tea_adapter
                        // Toast.makeText(context,"Успешно обновлено",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Обновление в говне", Toast.LENGTH_SHORT).show()
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
        getTeaData()
        getCoffeeData()
        tea_adapter.notifyDataSetChanged()
        coffee_adapter.notifyDataSetChanged()
        cofeeRV = binding.coffeeScroller
        teaRV = binding.teaScroller
        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HotDrinksFragment()
    }

}