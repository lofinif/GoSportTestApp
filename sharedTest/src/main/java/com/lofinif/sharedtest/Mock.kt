package com.lofinif.sharedtest

import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.data.menu.dto.Categories
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.data.menu.dto.Food
import com.lofinif.gosporttestapp.data.menu.dto.FoodDetails
import com.lofinif.gosporttestapp.data.menu.dto.FoodList
import com.lofinif.gosporttestapp.data.menu.dto.FoodsDetails
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel

val foodDetailsMock
    get() = FoodDetails("224", "Name", "someUrl",
        "Category", "1", "2", "3",
        "4", "5", "6", "7", "8",
        "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20")

val foodEntityMock
    get() = FoodEntity("1", "name", "description", "category", "imageUrl")

val stringSharedPrefMock
    get() = "1:strCategory"

val foodEntityListMock
    get() = listOf(FoodEntity("1", "name", "description", "category", "imageUrl"))

val categoriesMock
    get() = Categories(listOf(categoryMock))

val foodDetailsModelMock
    get() = FoodDetailsModel("1", "name","image","category","description")

val categoryMock
    get() = Category("1", "strCategory")
val categoryListMock
    get() = listOf(Category("1", "strCategory"))

val categoryModelMock
    get() = Category("1", "category")

val listAdsMock
    get() = listOf(adsPosterMock)

val adsPosterMock
    get() = AdsPoster(1, 1234)

val foodsDetailsMock
    get() = FoodsDetails(mealsFoodsDetailsMock)

val mealsFoodsDetailsMock
    get() = listOf(foodDetailsMock)

val mealsFoodListMock
    get() = listOf(foodMock)

val foodMock
    get() = Food("strMeal", "1")

val foodListMock
    get() = FoodList(mealsFoodListMock)