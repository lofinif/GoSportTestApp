package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class FoodsDetails(
    val meals: List<FoodDetails>
)
