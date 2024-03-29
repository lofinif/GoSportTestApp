package com.lofinif.gosporttestapp.data.menu

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import com.lofinif.gosporttestapp.R
import com.lofinif.gosporttestapp.data.BaseRepo
import com.lofinif.gosporttestapp.data.CallResult
import com.lofinif.gosporttestapp.data.local.FoodDao
import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.data.menu.dto.Categories
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.data.menu.dto.FoodDetails
import com.lofinif.gosporttestapp.data.network.ApiService
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.MenuViewModel
import com.lofinif.gosporttestapp.ui.menu.state.MenuCategoryScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: FoodDao,
    private val entityMapper: BaseMapper<FoodDetails, FoodEntity>,
    private val sharedPreferences: SharedPreferences,
    private val mapperShared: BaseMapper<String, Category>
) : MenuRepository, BaseRepo() {

    override suspend fun getCategories(): List<Category> {
        val categoryList = mutableListOf<Category>()
        val set: MutableSet<String> = HashSet()

      val response = safeApiCall { apiService.getCategories() }
        when(response){
            is CallResult.Success -> {
                for (category in response.value.categories) {
                    val categoryString = "${category.idCategory}:${category.strCategory}"
                    set.add(categoryString)
                }
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit().remove("category").apply()
                    sharedPreferences.edit().putStringSet("category", set).apply()
                }
                return response.value.categories
            }
            else -> {
                withContext(Dispatchers.IO) {
                    val categoryFromSharedPref = sharedPreferences.getStringSet("category", null)
                    categoryFromSharedPref?.map { mapperShared.map(it) }?.sortedBy {
                        it.idCategory.toInt()
                    }?.forEach { categoryList.add(it) }
                }
                    Log.e(MenuViewModel.TAG, "Couldn't reach server")
                    return categoryList
            }
        }
    }

    override val listAds: List<AdsPoster>
        get() = listOf(
            AdsPoster(1, R.drawable.ad_poster),
            AdsPoster(2, R.drawable.ad_poster),
            AdsPoster(3, R.drawable.ad_poster)
        )

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getDetailsFood(category: String): List<FoodEntity> = supervisorScope {
        val foodsDetails = mutableListOf<FoodEntity>()
        val jobs = mutableListOf<Deferred<FoodDetails>>()

        try {
            val remoteList = apiService.getListFoods(category)
            remoteList.meals?.forEach {
                jobs.add(
                    async {
                        apiService.getDetailsFood(it.idMeal).meals[0]
                    }
                )
            }
            jobs.forEachIndexed { index, it ->
                try {
                    val foodEntity = entityMapper.map(it.await())
                    dao.insertFood(foodEntity)
                    foodsDetails.add(foodEntity)
                } catch (e: Exception) {
                    Log.e("MenuRepositoryImpl", "$index - Exception caught ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e("MenuRepositoryImpl", "Couldn't reach server")
            return@supervisorScope dao.getFoodsByCategory(category)
        }
        foodsDetails
    }
}