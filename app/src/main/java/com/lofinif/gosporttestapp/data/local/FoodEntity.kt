package com.lofinif.gosporttestapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_list")
data class FoodEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
)
