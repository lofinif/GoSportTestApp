package com.lofinif.gosporttestapp.domain.repository

import com.lofinif.gosporttestapp.data.menu.dto.Category

import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.data.menu.dto.Categories

interface MenuRepository {
     suspend fun getCategories(): List<Category>
     val listAds: List<AdsPoster>
     suspend fun getDetailsFood(category: String): List<FoodEntity>
}