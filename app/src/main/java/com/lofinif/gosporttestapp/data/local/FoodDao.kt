package com.lofinif.gosporttestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
abstract class FoodDao {

@Query("SELECT * FROM food_list")
abstract fun getFoods(): List<FoodEntity>

@Query("SELECT * FROM food_list WHERE category = :category")
abstract fun getFoodsByCategory(category: String): List<FoodEntity>

@Query("DELETE FROM food_list")
abstract fun cleanFoods()

@Query("DELETE FROM food_list WHERE category = :category")
abstract fun deleteFoodsByCategory(category: String)

@Insert(onConflict = OnConflictStrategy.REPLACE)
abstract fun insertFood(food: FoodEntity)

}