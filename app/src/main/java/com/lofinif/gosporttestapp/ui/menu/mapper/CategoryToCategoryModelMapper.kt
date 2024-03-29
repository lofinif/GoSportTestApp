package com.lofinif.gosporttestapp.ui.menu.mapper

import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel
import javax.inject.Inject

class CategoryToCategoryModelMapper @Inject constructor(): BaseMapper<Category, CategoryModel> {
    override fun map(item: Category): CategoryModel =
        CategoryModel(
            id = item.idCategory,
            category = item.strCategory,
    )
}