package com.lofinif.gosporttestapp.data.network

import com.lofinif.gosporttestapp.data.menu.dto.Categories
import com.lofinif.gosporttestapp.data.menu.dto.FoodList
import com.lofinif.gosporttestapp.data.menu.dto.FoodsDetails
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    companion object{
        fun create(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }

    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Categories

    @GET("/api/json/v1/1/filter.php")
    suspend fun getListFoods(@Query("c") category: String): FoodList

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getDetailsFood(@Query("i") id: String): FoodsDetails
}