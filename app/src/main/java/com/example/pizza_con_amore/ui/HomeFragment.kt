package com.example.pizza_con_amore.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.*
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.FragmentHomeBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter.c_Listener
import com.example.pizza_con_amore.ui.category_fragments.BreakfastFragment
import com.example.pizza_con_amore.ui.category_fragments.ColdDrinksFragment
import com.example.pizza_con_amore.ui.category_fragments.HotDrinksFragment
import com.example.pizza_con_amore.ui.category_fragments.LunchFragment
import com.google.firebase.database.*


open class HomeFragment : Fragment(), c_Listener {
    private var _binding: FragmentHomeBinding? = null
    lateinit var pca_base: DatabaseReference
    lateinit var categoryRV: RecyclerView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)

        binding.apply {

            categoryRV = categoryScroller
            categoryRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryArrayList = arrayListOf<CategoryData>()
            getCategoryData()
        }
        return root
    }

    private fun getCategoryData() {
        pca_base = FirebaseDatabase.getInstance().getReference(NODE_CATEGORIES)
        pca_base.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryArrayList.clear()
                if (snapshot.exists()) {
                    for (categorySnapsot in snapshot.children) {
                        val category = categorySnapsot.getValue(CategoryData::class.java)
                        categoryArrayList.add(category!!)
                    }
                    categoryRV.adapter = CategoryAdapter(this@HomeFragment, categoryArrayList)
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
    private fun openSubFragment(whatFragment: Fragment, whereToHold: Int) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(whereToHold, whatFragment, "findFragment")
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onClick(category: CategoryData) {

        when (category.categoryName) {
            "01_breakfast" -> { ACTIVE_CATEGORY = BREAKFAST;
                openSubFragment(BreakfastFragment(), R.id.main_food_menu)
            }
            "02_pizza" -> {ACTIVE_CATEGORY = PIZZA;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "03_focaccia" -> {ACTIVE_CATEGORY = FOCACCIA;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "04_lunch" -> {ACTIVE_CATEGORY = LUNCH;
                openSubFragment(LunchFragment(), R.id.main_food_menu)
            }
            "05_pasta" -> {ACTIVE_CATEGORY = PASTA;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "06_hot_snacks" -> {ACTIVE_CATEGORY = HOT_SNACKS;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "07_salad" -> {ACTIVE_CATEGORY = SALAD;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "08_raviolli" -> {ACTIVE_CATEGORY = RAVIOLLI;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "09_hot_meal" -> {ACTIVE_CATEGORY = HOT_MEAL;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "10_ice_cream" -> {ACTIVE_CATEGORY = ICE_CREAM;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "11_milkshakes" -> {ACTIVE_CATEGORY = MILKSHAKE;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            "12_hot_drinks" -> {ACTIVE_CATEGORY = HOT_DRINKS;
                openSubFragment(HotDrinksFragment(), R.id.main_food_menu)
            }
            "13_cold_drinks" -> {ACTIVE_CATEGORY = COLD_DRINKS;
                openSubFragment(ColdDrinksFragment(), R.id.main_food_menu)
            }
            "14_freezing" -> {ACTIVE_CATEGORY = FREEZING;
                openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)
            }
            else -> {ACTIVE_CATEGORY = ACTIVE_CATEGORY }
        }
        livedata.postValue(ACTIVE_CATEGORY)
        pca_base = FirebaseDatabase.getInstance().reference
        pca_base.child(ACTIVE).setValue(ACTIVE_CATEGORY).addOnSuccessListener {
            //Toast.makeText(context, ACTIVE_CATEGORY,Toast.LENGTH_SHORT).show()
        }.addOnFailureListener()
        {
            Toast.makeText(context,"Сохранение в говне!",Toast.LENGTH_SHORT).show()
        }

        //parentFragmentManager.beginTransaction().detach(this).commit ()
     //parentFragmentManager.beginTransaction().attach(this).commit ()}
        //Toast.makeText(context, ACTIVE_CATEGORY, Toast.LENGTH_SHORT).show()
    }


}