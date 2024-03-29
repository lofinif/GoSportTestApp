package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val strMeal: String,
    val idMeal: String,
)
