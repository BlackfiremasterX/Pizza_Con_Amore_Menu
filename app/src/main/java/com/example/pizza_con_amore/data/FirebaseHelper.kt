package com.example.pizza_con_amore.data

import androidx.lifecycle.MutableLiveData
import com.example.pizza_con_amore.data.firebase.FirebaseDataStructure
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//Словари
var categoryBuffer = mutableMapOf<Int,String>()


//Сет списков
lateinit var foodArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var compoundArrayList: ArrayList<FirebaseDataStructure.IngredientsData>
lateinit var complexArrayList: ArrayList<FirebaseDataStructure.FoodData>
lateinit var todayArrayList: ArrayList<FirebaseDataStructure.FoodData>
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
const val COMPOUND = "Состав"

const val CATEGORY_ID = "categoryId"
const val CATEGORY_NAME = "categoryName"
const val CATEGORY_TITLE = "categoryTitle"
const val CATEGORY_IMG = "categoryImg"

//Категории константы путей
//Основа
const val BREAKFAST = "01_breakfast"
const val PIZZA = "02_pizza"
const val PASTA = "03_pasta"
const val SOUP = "04_soup"
const val HOT_MEAL = "05_hot_meal"
const val HOT_SNACKS = "06_hot_snacks"
const val BIG_PIES = "07_big_pies"
const val CAKES = "08_cakes"
const val FREEZING_SELL = "09_freezing"
const val HOT_DRINKS = "10_hot_drinks"
const val COLD_DRINKS = "11_cold_drinks"

//Побочка
const val TEA = "tea"
const val COFFEE = "coffee"
const val ALCO = "alco"


const val JUICE = "juice"
const val NOALCO = "no_alco"
const val TODAY = "Сегодня в меню"

//Отключено
const val RAVIOLLI = "08_raviolli"
const val ICE_CREAM = "10_ice_cream"
const val FOCACCIA = "03_focaccia"
const val LUNCH = "04_lunch"
const val SALAD = "07_salad"
const val MILKSHAKE = "11_milkshakes"

const val DEFAULT_FOOD_ITEM = "02_salmone"

//Операнды
lateinit var REF_PCA_BASE_ROOT: DatabaseReference
var ACTIVE_CATEGORY: String = PIZZA
var ACTIVE_FOOD: String = DEFAULT_FOOD_ITEM
var ACTIVE_FOOD_TITLE: String = "Пустое блюдо"
var ACTIVE_FOOD_PRICE: String = "Нету"
var ACTIVE_FOOD_MASS: String = "Нету"
var ACTIVE_FOOD_DESCRIPTION: String = "Описания пока что нет, но дядя Шеф скоро его обязательно напишет"
var ACTIVE_FOOD_IMAGE_LINK: String = "https://firebasestorage.googleapis.com/v0/b/pizza-con-amore-base.appspot.com/o/img%2Fsoup%2Fuha_antipohmel.jpg?alt=media&token=d6ecfdbc-f4fd-4525-a4f5-b4b4af5930b9"


var CURRENT_FOOD_ITEM: String = DEFAULT_FOOD_ITEM
val livedata = MutableLiveData(BREAKFAST)

fun getCategoryRef(category: String) = FirebaseDatabase.getInstance().reference.child(
    NODE_CATEGORIES
).child(category).child(category + "_list")

fun getCompoundRef(food: String) = FirebaseDatabase.getInstance().reference
    .child(NODE_CATEGORIES)
    .child(ACTIVE_CATEGORY)
    .child(ACTIVE_CATEGORY +"_list")
    .child(ACTIVE_FOOD)
    .child(COMPOUND)

fun getLunchComplexRef(complex: String) = FirebaseDatabase.getInstance().reference.child(
    NODE_CATEGORIES
).child(LUNCH).child(LUNCH + "_list").child("complex_lunch_list")
fun getLunchTodayRef(today: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    LUNCH
).child(LUNCH + "_list").child(TODAY)
fun getAddonRef(addons: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(addons).child(
    NODE_ADDONS
)
fun getTeaRef(tea: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    HOT_DRINKS
).child(HOT_DRINKS +"_list").child(TEA)
fun getCoffeeRef(coffee: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    HOT_DRINKS
).child(HOT_DRINKS +"_list").child(COFFEE)
fun getAlcoRef(Alco: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    COLD_DRINKS
).child(COLD_DRINKS +"_list").child(ALCO)
fun getNoAlcoRef(NoAlco: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    COLD_DRINKS
).child(COLD_DRINKS +"_list").child(NOALCO)
fun getJuiceRef(Juice: String) = FirebaseDatabase.getInstance().reference.child(NODE_CATEGORIES).child(
    COLD_DRINKS
).child(COLD_DRINKS +"_list").child(JUICE)




fun openReference(path:String)
{
    REF_PCA_BASE_ROOT = FirebaseDatabase.getInstance().getReference(path)

}