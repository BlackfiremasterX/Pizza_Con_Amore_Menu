package com.example.pizza_con_amore.firebase

class FirebaseDataStructure {
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
        val foodId: Int? = null,
        val foodName: String? = null,
        val foodPrice: String? = null,
        val foodMass: String? = null,
        val foodDescription: String? = null,
        val foodIngredientList: Array<String>? = null,
        val foodCategory: String? = null,
        val foodImageLink: String? = null
    )

    data class IngredientsData(
        val ingredientId: Int? = null,
        val ingredientName: String? = null,
        val ingredientImageLink: String? = null,
        val ingredientFoodList: Array<String>? = null
    )

    data class CategoryData_fromDB(
        val categoryTitle: String? = null,
        val categoryImageLink: String? = null
    ) {
        fun categoryImageLink(toString: String) {

        }
    }


}


