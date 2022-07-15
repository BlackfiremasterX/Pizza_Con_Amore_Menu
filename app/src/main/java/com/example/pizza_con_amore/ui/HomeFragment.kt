package com.example.pizza_con_amore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.NODE_CATEGORIES
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.categoryArrayList
import com.example.pizza_con_amore.databinding.FragmentHomeBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.adapter.DB_CategoryAdapter
import com.example.pizza_con_amore.firebase.adapter.DB_FoodAdapter

import com.google.firebase.database.*


open class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    var menuItemAdapter: DB_FoodAdapter? = null


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
            categoryRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryArrayList = arrayListOf<FirebaseDataStructure.CategoryData>()
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
                        val category =
                            categorySnapsot.getValue(FirebaseDataStructure.CategoryData::class.java)
                        categoryArrayList.add(category!!)
                    }
                    categoryRV.adapter = activity?.let { DB_CategoryAdapter(categoryArrayList, it) }
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

}