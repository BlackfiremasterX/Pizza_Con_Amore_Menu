package com.example.pizza_con_amore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)
    var id:Int
):Serializable