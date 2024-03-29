package com.lofinif.gosporttestapp.data.local

import com.lofinif.gosporttestapp.data.menu.dto.FoodDetails
import com.lofinif.gosporttestapp.ui.BaseMapper
import javax.inject.Inject


class FoodDetailsToFoodEntityMapper @Inject constructor(): BaseMapper<FoodDetails, FoodEntity> {
    override fun map(item: FoodDetails): FoodEntity {

        val array = arrayOf(item.strIngredient1, item.strIngredient2, item.strIngredient3, item.strIngredient4, item.strIngredient5, item.strIngredient6,
            item.strIngredient7,item.strIngredient8,item.strIngredient9,item.strIngredient10,item.strIngredient11,
            item.strIngredient12, item.strIngredient13, item.strIngredient14, item.strIngredient15, item.strIngredient16,
            item.strIngredient17, item.strIngredient18, item.strIngredient19, item.strIngredient20)


        val filteredArray = array.filter { !it.isNullOrEmpty() }
        val description = StringBuilder()
        for (i in filteredArray.indices) {
            description.append(filteredArray[i]).append(",")
        }
        return FoodEntity(
            id = item.idMeal,
            name = item.strMeal,
            imageUrl = item.strMealThumb,
            category = item.strCategory,
            description = description.removeSuffix(",").toString(),
        )
    }
}