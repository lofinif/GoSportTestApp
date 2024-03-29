package com.lofinif.gosporttestapp.ui.menu.mapper

import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel
import javax.inject.Inject

class FoodEntityToFoodDetailsModelMapper @Inject constructor(): BaseMapper<FoodEntity, FoodDetailsModel> {

    override fun map(item: FoodEntity): FoodDetailsModel = FoodDetailsModel(item.id, item.name, item.imageUrl, item.category, item.description)
}