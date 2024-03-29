package com.lofinif.gosporttestapp.ui.menu.state

import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel

sealed interface MenuCategoryScreenState {
    data object Loading: MenuCategoryScreenState
    data object Error: MenuCategoryScreenState
    data class Loaded(val model: List<CategoryModel>): MenuCategoryScreenState
}