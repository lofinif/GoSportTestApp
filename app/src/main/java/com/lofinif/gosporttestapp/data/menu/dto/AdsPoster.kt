package com.lofinif.gosporttestapp.data.menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdsPoster(
    val id: Int,
    val source: Int
)