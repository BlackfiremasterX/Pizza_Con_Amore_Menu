package com.example.pizza_con_amore

import androidx.lifecycle.MutableLiveData
import com.example.pizza_con_amore.firebase.FirebaseDataStructure
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//Словари
var categoryBuffer = mutableMapOf<Int,String>()


//Сет списков
lateinit var foodArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var complexArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var alcoArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var noAlcoArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var coffeeArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var teaArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var categoryArrayList: ArrayList<FirebaseDataStructure.CategoryData>
lateinit var ingredientArrayList: ArrayList<FirebaseDataStructure.IngredientsData>
lateinit var addonsArrayList: ArrayList<FirebaseDataStructure.AddonsData>



//Константы
const val NODE_CATEGORIES = "Category"
const val NODE_ADDONS = "Addons"
const val INGREDIENT_PATH = "Ingredients"
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
const val TEA = "tea"
const val COFFEE = "coffee"
const val ALCO = "alco"
const val NOALCO = "no_alco"



const val DEFAULT_FOOD_ITEM = "01_fegato"

//Операнды
lateinit var REF_PCA_BASE_ROOT: DatabaseReference
var ACTIVE_CATEGORY: String = PIZZA
var CURRENT_FOOD_ITEM: String = DEFAULT_FOOD_ITEM
val livedata = MutableLiveData(BREAKFAST)

fun getCategoryRef(category: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(category).child(category + "_list")
fun getLunchComplexRef(complex: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(LUNCH).child(LUNCH + "_list").child("complex_lunch_list")
fun getAddonRef(addons: String) = FirebaseDatabase.getInstance().reference.child(NODE_ADDONS)
fun getTeaRef(tea: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(HOT_DRINKS).child(HOT_DRINKS+"_list").child(TEA)
fun getCoffeeRef(coffee: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(HOT_DRINKS).child(HOT_DRINKS+"_list").child(COFFEE)
fun getAlcoRef(Alco: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(COLD_DRINKS).child(COLD_DRINKS+"_list").child(ALCO)
fun getNoAlcoRef(NoAlco: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(COLD_DRINKS).child(COLD_DRINKS+"_list").child(NOALCO)




fun openReference(path:String)
{
    REF_PCA_BASE_ROOT = FirebaseDatabase.getInstance().getReference(path)

}