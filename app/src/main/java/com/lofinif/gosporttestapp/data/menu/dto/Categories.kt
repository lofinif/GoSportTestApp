package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val categories: List<Category>
)