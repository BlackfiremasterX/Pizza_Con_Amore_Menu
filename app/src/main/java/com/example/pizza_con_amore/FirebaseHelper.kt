package com.example.pizza_con_amore

import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//Словари
var categoryBuffer = mutableMapOf<Int,String>()


//Сет списков
lateinit var foodArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var categoryArrayList: ArrayList<FirebaseDataStructure.CategoryData>

//Операнды
lateinit var REF_PCA_BASE_ROOT: DatabaseReference
var ACTIVE_CATEGORY: String = "02_pizza"




//Константы
const val NODE_CATEGORIES = "Category"
const val CATEGORY_ID = "categoryId"
const val CATEGORY_NAME = "categoryName"
const val CATEGORY_TITLE = "categoryTitle"
const val CATEGORY_IMG = "categoryImg"




fun initFirebase(path:String)
{
    REF_PCA_BASE_ROOT = FirebaseDatabase.getInstance().getReference(path)

}