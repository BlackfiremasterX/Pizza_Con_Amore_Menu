package com.example.pizza_con_amore.interfaces


import com.example.pizza_con_amore.entities.Category
import com.example.pizza_con_amore.entities.Meal
import com.example.pizza_con_amore.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>


}