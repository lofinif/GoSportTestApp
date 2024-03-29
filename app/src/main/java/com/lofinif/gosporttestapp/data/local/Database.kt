package com.lofinif.gosporttestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun foodDao(): FoodDao
}