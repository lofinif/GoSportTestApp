package com.lofinif.gosporttestapp.ui.menu.mapper

import com.google.common.truth.Truth
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.data.local.FoodDetailsToFoodEntityMapper
import com.lofinif.sharedtest.categoryMock
import com.lofinif.sharedtest.categoryModelMock
import com.lofinif.sharedtest.foodDetailsMock
import org.junit.Test

class CategoryToCategoryModelMapperTest: BaseTest() {

    private lateinit var mapper: CategoryToCategoryModelMapper

    private val dto = categoryMock

    override fun setUp() {
        super.setUp()
        mapper = CategoryToCategoryModelMapper()
    }

    @Test
    fun `id is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.id).isEqualTo(dto.idCategory)
    }
    @Test
    fun `category is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.category).isEqualTo(dto.strCategory)
    }

}