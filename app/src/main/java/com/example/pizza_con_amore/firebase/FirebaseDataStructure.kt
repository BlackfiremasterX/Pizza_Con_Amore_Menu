package com.example.pizza_con_amore.firebase

class FirebaseDataStructure {
    data class ActiveCategory(
        val activeCategory: String? = null
    )


    data class UserData(
        val userId: Int? = null,
        val userNickname: String? = null,
        val userEmail: String? = null,
        val userPassword: String? = null,
        val userFName: String? = null,
        val userSName: String? = null,
        val userAvatarLink: String? = null
    )

    data class CategoryData(
        val categoryId: String? = null,
        val categoryName: String? = null,
        val categoryTitle: String? = null,
        val categoryImageLink: String? = null
    )

    data class FoodData(
        val foodId: String? = null,
        val foodName: String? = null,
        val foodPrice: String? = null,
        val foodMass: String? = null,
        val foodDescription: String? = null,
        val foodIngredientList: String? = null,
        val foodCategory: String? = null,
        val foodImageLink: String? = null
    )

    data class IngredientsData(
        val ingredientId: String? = null,
        val ingredientName: String? = null,
        val ingredientPrice: String? = null,
        val ingredientMass: String? = null,
        val ingredientFoodList: String? = null,
        val ingredientImageLink: String? = null
    )


}


