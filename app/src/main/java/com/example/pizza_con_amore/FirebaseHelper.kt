package com.example.pizza_con_amore

import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//Словари
var categoryBuffer = mutableMapOf<Int,String>()


//Сет списков
lateinit var foodArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var categoryArrayList: ArrayList<FirebaseDataStructure.CategoryData>
lateinit var ingredientArrayList: ArrayList<FirebaseDataStructure.IngredientsData>






//Константы
const val NODE_CATEGORIES = "Category"
const val ACTIVE = "Active"
const val CATEGORY_ID = "categoryId"
const val CATEGORY_NAME = "categoryName"
const val CATEGORY_TITLE = "categoryTitle"
const val CATEGORY_IMG = "categoryImg"

//Категории константы путей
const val BREAKFAST = "01_breakfast"
const val PIZZA = "02_pizza"
const val FOCACCIA = "03_focaccia"
const val LUNCH = "04_lunch"
const val PASTA = "05_pasta"
const val HOT_SNACKS = "06_hot_snacks"
const val SALAD = "07_salad"
const val RAVIOLLI = "08_raviolli"
const val HOT_MEAL = "09_hot_meal"
const val ICE_CREAM = "10_ice_cream"
const val MILKSHAKE = "11_milkshakes"
const val HOT_DRINKS = "12_hot_drinks"
const val COLD_DRINKS = "13_cold_drinks"
const val FREEZING = "14_freezing"

const val DEFAULT_FOOD_ITEM = "01_fegato"







//Операнды
lateinit var REF_PCA_BASE_ROOT: DatabaseReference
var ACTIVE_CATEGORY: String = BREAKFAST
var CURRENT_FOOD_ITEM: String = DEFAULT_FOOD_ITEM


var categories_dynamic_ref =  FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(ACTIVE_CATEGORY).child(ACTIVE_CATEGORY + "_list")



fun openReference(path:String)
{
    REF_PCA_BASE_ROOT = FirebaseDatabase.getInstance().getReference(path)

}