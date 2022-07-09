package com.example.pizza_con_amore.local_adapter

class LocalDataClasses {


    data class CategoryLocalData(
        val imageId: Int,
        val title:String
    )

    data class FoodItemLocalData(
        val imageId: Int,
        val title:String,
        val price:String,
        val mass:String
    )



}


