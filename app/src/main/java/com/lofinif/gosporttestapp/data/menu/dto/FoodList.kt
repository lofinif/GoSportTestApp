package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class FoodList(
    val meals: List<Food>?
)