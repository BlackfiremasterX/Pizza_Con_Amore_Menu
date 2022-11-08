package com.example.pizza_con_amore.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.IndividualLunchFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.*
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.*


open class LunchFragment : HomeFragment() {

    lateinit var complexRV: RecyclerView
    lateinit var todayRV: RecyclerView
    lateinit var complex_adapter:LunchComplexAdapter
    lateinit var today_adapter:TodayLunchAdapter
    private var _binding: IndividualLunchFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = IndividualLunchFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            complexRV = complexScroll
            todayRV = todayLunchScroll

            complexRV.layoutManager = GridLayoutManager(context, 1)
            todayRV.layoutManager = GridLayoutManager(context, 2)

            complexArrayList = arrayListOf<FoodData>()
            todayArrayList = arrayListOf<FoodData>()
            complex_adapter = LunchComplexAdapter(complexArrayList,context!!)
            today_adapter = TodayLunchAdapter(todayArrayList,context!!)
            onCategoryClick(CategoryData())


        }
        return root
    }

    private fun getTodayLunchData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getLunchTodayRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    todayArrayList.clear()
                    if (snapshot.exists()) {
                        for (complexSnapsot in snapshot.children) {
                            try{
                                val today = complexSnapsot.getValue(FoodData::class.java)
                                todayArrayList.add(today!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(complexSnapsot.value)
                            }}
                        todayRV.adapter = TodayLunchAdapter(todayArrayList, context!!)
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
    private fun getComplexData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getLunchComplexRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    complexArrayList.clear()
                    if (snapshot.exists()) {
                        for (complexSnapsot in snapshot.children) {
                            try{
                                val complex = complexSnapsot.getValue(FoodData::class.java)
                                complexArrayList.add(complex!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(complexSnapsot.value)
                            }}
                        complexRV.adapter = LunchComplexAdapter(complexArrayList, context!!)
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

    override fun onCategoryClick(category: CategoryData) {
        getComplexData()
        getTodayLunchData()
        complex_adapter.notifyDataSetChanged()
        complexRV = binding.complexScroll
        super.onCategoryClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = LunchFragment()
    }

}