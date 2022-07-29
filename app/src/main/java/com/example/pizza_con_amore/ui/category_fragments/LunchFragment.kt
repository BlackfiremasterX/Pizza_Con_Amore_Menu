package com.example.pizza_con_amore.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.databinding.IndividualLunchFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.ColdDrinksAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
import com.example.pizza_con_amore.firebase.adapter.HotDrinksAdapter
import com.example.pizza_con_amore.firebase.adapter.LunchComplexAdapter
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.*


open class LunchFragment : HomeFragment() {

    lateinit var complexRV: RecyclerView
    lateinit var complex_adapter:LunchComplexAdapter
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
            complexRV.layoutManager = GridLayoutManager(context, 1)

            complexArrayList = arrayListOf<FoodData>()
            complex_adapter = LunchComplexAdapter(complexArrayList,context!!)
            onClick(CategoryData())


        }
        return root
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

    override fun onClick(category: CategoryData) {
        getComplexData()
        complex_adapter.notifyDataSetChanged()
        complexRV = binding.complexScroll
        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = LunchFragment()
    }

}