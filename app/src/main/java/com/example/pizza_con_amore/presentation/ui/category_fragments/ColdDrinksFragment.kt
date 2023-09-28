package com.example.pizza_con_amore.presentation.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.data.alcoArrayList
import com.example.pizza_con_amore.data.compoundArrayList
import com.example.pizza_con_amore.data.getJuiceRef
import com.example.pizza_con_amore.data.getNoAlcoRef
import com.example.pizza_con_amore.data.livedata
import com.example.pizza_con_amore.data.noAlcoArrayList
import com.example.pizza_con_amore.databinding.IndividualColdDrinksFragmentBinding
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.data.firebase.adapter.ColdDrinksAdapter
import com.example.pizza_con_amore.presentation.ui.HomeFragment
import com.google.firebase.database.*


open class ColdDrinksFragment : HomeFragment() {

    lateinit var noalcoRV: RecyclerView
    lateinit var alcoRV: RecyclerView
    lateinit var alco_drinks_adapter: ColdDrinksAdapter
    lateinit var noalco_drinks_adapter: ColdDrinksAdapter
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
            compoundArrayList = arrayListOf<IngredientsData>()
            alco_drinks_adapter = ColdDrinksAdapter(alcoArrayList,requireContext())
            noalco_drinks_adapter = ColdDrinksAdapter(noAlcoArrayList,requireContext())
            onCategoryClick(CategoryData())
            getJuiceData()
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
    private fun getJuiceData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getJuiceRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    alcoArrayList.clear()
                    if (snapshot.exists()) {
                        for (juiceSnapsot in snapshot.children) {
                            try{
                                val juice = juiceSnapsot.getValue(FoodData::class.java)
                                alcoArrayList.add(juice!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(juiceSnapsot.value)
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

    override fun onCategoryClick(category: CategoryData) {
        getJuiceData()
        getNoAlcoDrinksData()
        alco_drinks_adapter.notifyDataSetChanged()
        noalco_drinks_adapter.notifyDataSetChanged()
        noalcoRV = binding.notAlcoholScroll
        alcoRV = binding.alcoholScroll
        super.onCategoryClick(category)
    }

    override fun onFoodItemClick(food: FoodData, position: Int) {
        TODO("Not yet implemented")
    }


    companion object {
        @JvmStatic
        fun newInstance() = ColdDrinksFragment()
    }

}