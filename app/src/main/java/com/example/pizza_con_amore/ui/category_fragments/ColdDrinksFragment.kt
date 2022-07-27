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
import com.example.pizza_con_amore.databinding.FragmentActiveCategoryBinding
import com.example.pizza_con_amore.databinding.IndividualColdDrinksFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.ColdDrinksAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.*


open class ColdDrinksFragment : HomeFragment() {

    lateinit var noalcoRV: RecyclerView
    lateinit var alcoRV: RecyclerView
    lateinit var alco_drinks_adapter:ColdDrinksAdapter
    lateinit var noalco_drinks_adapter:ColdDrinksAdapter
    private var _binding: IndividualColdDrinksFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = IndividualColdDrinksFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            noalcoRV = notAlcoholScroll
            alcoRV = alcoholScroll

            noalcoRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            alcoRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            noAlcoArrayList = arrayListOf<FoodData>()
            alcoArrayList = arrayListOf<FoodData>()
            alco_drinks_adapter = ColdDrinksAdapter(alcoArrayList,context!!)
            noalco_drinks_adapter = ColdDrinksAdapter(noAlcoArrayList,context!!)
            onClick(CategoryData())
            getAlcoDrinksData()
            getNoAlcoDrinksData()

        }
        return root
    }

    private fun getNoAlcoDrinksData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getNoAlcoRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    noAlcoArrayList.clear()
                    if (snapshot.exists()) {
                        for (noalcoSnapsot in snapshot.children) {
                            try{
                                val noalco = noalcoSnapsot.getValue(FoodData::class.java)
                                noAlcoArrayList.add(noalco!!) //food?.let { noAlcoArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(noalcoSnapsot.value)
                            }}
                        noalcoRV.adapter = ColdDrinksAdapter(noAlcoArrayList, context!!)
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
    private fun getAlcoDrinksData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getAlcoRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    alcoArrayList.clear()
                    if (snapshot.exists()) {
                        for (alcoSnapsot in snapshot.children) {
                            try{
                                val alco = alcoSnapsot.getValue(FoodData::class.java)
                                alcoArrayList.add(alco!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(alcoSnapsot.value)
                            }}
                        alcoRV.adapter = ColdDrinksAdapter(alcoArrayList, context!!)
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

    override fun onClick(category: CategoryData) {
        getAlcoDrinksData()
        getNoAlcoDrinksData()
        alco_drinks_adapter.notifyDataSetChanged()
        noalco_drinks_adapter.notifyDataSetChanged()
        noalcoRV = binding.notAlcoholScroll
        alcoRV = binding.alcoholScroll
        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = ColdDrinksFragment()
    }

}