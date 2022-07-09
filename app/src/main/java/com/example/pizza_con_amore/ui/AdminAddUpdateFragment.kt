package com.example.pizza_con_amore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pizza_con_amore.databinding.FragmentAdminAddUpdateBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AdminAddUpdateFragment : HomeFragment() {

    private lateinit var pca_base: DatabaseReference
    private var _binding: FragmentAdminAddUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAdminAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

                binding.apply {

                    addCategory.setOnClickListener {

                        val categoryId = categoryId.text.toString()
                        val categoryName = categoryName.text.toString()
                        val categoryTitle = categoryTitle.text.toString()
                        val categoryImg = categoryImageLink.text.toString()

                        pca_base = FirebaseDatabase.getInstance().getReference("Category")
                        val category = FirebaseDataStructure.CategoryData(categoryId,categoryName,categoryTitle,categoryImg)
                        pca_base.child(categoryName).setValue(category).addOnSuccessListener {
                            binding.categoryId.text.clear()
                            binding.categoryName.text.clear()
                            binding.categoryTitle.text.clear()
                            binding.categoryImageLink.text.clear()
                            Toast.makeText(context,"Успешно сохранено",Toast.LENGTH_SHORT).show()

                        }.addOnFailureListener()
                        {
                            Toast.makeText(context,"Сохранение в говне!",Toast.LENGTH_SHORT).show()
                        }
                    }



                    addCategory.setOnClickListener {

                        val categoryId = categoryId.text.toString()
                        val categoryName = categoryName.text.toString()
                        val categoryTitle = categoryTitle.text.toString()
                        val categoryImg = categoryImageLink.text.toString()

                        pca_base = FirebaseDatabase.getInstance().getReference("Category")
                        val category = FirebaseDataStructure.CategoryData(categoryId,categoryName,categoryTitle,categoryImg)
                        pca_base.child(categoryName).setValue(category).addOnSuccessListener {
                            binding.categoryId.text.clear()
                            binding.categoryName.text.clear()
                            binding.categoryTitle.text.clear()
                            binding.categoryImageLink.text.clear()
                            Toast.makeText(context,"Успешно сохранено",Toast.LENGTH_SHORT).show()

                        }.addOnFailureListener()
                        {
                            Toast.makeText(context,"Сохранение в говне!",Toast.LENGTH_SHORT).show()
                        }
                    }



                }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = AdminAddUpdateFragment()
    }
}