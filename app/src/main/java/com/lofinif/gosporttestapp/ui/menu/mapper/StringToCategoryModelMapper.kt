package com.lofinif.gosporttestapp.ui.menu.mapper

import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.ui.BaseMapper
import javax.inject.Inject

class StringToCategoryModelMapper @Inject constructor(): BaseMapper<String, Category> {
    override fun map(item: String): Category {
        val arr = item.split(":")
        return Category(idCategory = arr[0], strCategory = arr[1])
    }
}