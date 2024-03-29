package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val idCategory: String,
    val strCategory: String,
)
