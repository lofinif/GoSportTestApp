package com.lofinif.gosporttestapp.ui.menu.state

import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel

sealed interface MenuFoodScreenState {
    data object Loading: MenuFoodScreenState
    data object Error: MenuFoodScreenState
    data class Loaded(val model: List<FoodDetailsModel>): MenuFoodScreenState
}