package com.example.pizza_con_amore.ui

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza_con_amore.R
import com.example.pizza_con_amore.databinding.FragmentHomeBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.example.pizza_con_amore.firebase.adapter.DB_CategoryAdapter
import com.example.pizza_con_amore.local_adapter.CategoryLocalAdapter
import com.example.pizza_con_amore.local_adapter.FoodItemLocalAdapter
import com.example.pizza_con_amore.local_data.CategoryLocalData
import com.example.pizza_con_amore.local_data.FoodItemLocalData
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*


open class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    var clAdapter: CategoryLocalAdapter? = null
    var menuItemAdapter: FoodItemLocalAdapter? = null
    val CATEGORY_PATH = "Category"


    private lateinit var pca_base: DatabaseReference
    private lateinit var categoryRV: RecyclerView
    private lateinit var categoryArrayList: ArrayList<FirebaseDataStructure.CategoryData_fromDB>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        openSubFragment(ActiveCategoryFragment(), R.id.main_food_menu)

//        var categoryFullParameterList = ArrayList<CategoryLocalData>()
//        categoryFullParameterList.addAll(
//            fillDataInCategory(
//                getImageIdFromResources(R.array.category_img),
//                resources.getStringArray(R.array.category_name)
//            )
//        )




        binding.apply {


//            homeFoodScroller.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            clAdapter = context?.let { CategoryLocalAdapter(categoryFullParameterList, it) }
//            homeFoodScroller.adapter = clAdapter

            categoryRV = homeFoodScroller
            categoryRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryArrayList = arrayListOf<FirebaseDataStructure.CategoryData_fromDB>()

            getCategoryData()





        }
        return root
    }



    private fun getCategoryData() {
        pca_base = FirebaseDatabase.getInstance().getReference(CATEGORY_PATH)
        pca_base.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (categorySnapsot in snapshot.children) {
                        val category =
                            categorySnapsot.getValue(FirebaseDataStructure.CategoryData_fromDB::class.java)
                        categoryArrayList.add(category!!)
                    }

                    categoryRV.adapter = DB_CategoryAdapter(categoryArrayList)


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


    //Локальная подгрузка тест
    fun fillDataInCategory(imgArray: IntArray, titleArray: Array<String>): List<CategoryLocalData> {
        var listItemArray = ArrayList<CategoryLocalData>()
        for (n in titleArray.indices) {
            var listItem = CategoryLocalData(imgArray[n], titleArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageIdFromResources(imageArrayId: Int): IntArray {
        var chosenArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = chosenArray.length()
        val ids = IntArray(count)

        for (i in ids.indices) {
            ids[i] = chosenArray.getResourceId(i, 0)
        }
        chosenArray.recycle()
        return ids
    }

    fun fillDataInFoodItems(
        imgArray: IntArray,
        titleArray: Array<String>,
        priceArray: Array<String>,
        massArray: Array<String>
    ): Collection<FoodItemLocalData> {
        var listItemArray = ArrayList<FoodItemLocalData>()
        for (n in titleArray.indices) {
            var listItem =
                FoodItemLocalData(imgArray[n], titleArray[n], priceArray[n], massArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray

    }
}