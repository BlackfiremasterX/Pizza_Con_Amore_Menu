package com.example.pizza_con_amore.ui.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.databinding.IndividualBreakfastFragmentBinding
import com.example.pizza_con_amore.databinding.IndividualHotSnacksFragmentBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.AddonAdapter
import com.example.pizza_con_amore.firebase.adapter.FoodAdapter
import com.example.pizza_con_amore.ui.HomeFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


open class HotSnacksFragment : HomeFragment() {

    lateinit var baseRV: RecyclerView
    lateinit var addonsRV: RecyclerView
    lateinit var base_adapter:FoodAdapter
    lateinit var addons_adapter:AddonAdapter
    private var _binding: IndividualHotSnacksFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = IndividualHotSnacksFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            baseRV = breakfastScroller
            addonsRV = addonsScroller

            baseRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addonsRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            foodArrayList = arrayListOf<FoodData>()
            addonsArrayList = arrayListOf<AddonsData>()
            base_adapter = FoodAdapter(foodArrayList,context!!)
            addons_adapter = AddonAdapter(addonsArrayList,context!!)

            getAddonsData()
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
                        baseRV.adapter = FoodAdapter(foodArrayList, context!!)
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

    private fun getAddonsData() {
        livedata.observe(viewLifecycleOwner){
            pca_base = getAddonRef(it)
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    addonsArrayList.clear()
                    if (snapshot.exists()) {
                        for (addonsAdapter in snapshot.children) {
                            try{
                                val addon = addonsAdapter.getValue(AddonsData::class.java)
                                addonsArrayList.add(addon!!) //food?.let { foodArrayList.add(it) }
                            } catch (e:Exception){
                                println(e.message)
                                println(addonsAdapter.value)
                            }}
                        addonsRV.adapter = AddonAdapter(addonsArrayList, context!!)
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
        getFoodData()
        base_adapter.notifyDataSetChanged()
        addons_adapter.notifyDataSetChanged()
        baseRV = binding.breakfastScroller
        super.onClick(category)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HotSnacksFragment()
    }

}