package com.lofinif.gosporttestapp.ui.menu.mapper

import com.google.common.truth.Truth
import com.lofinif.BaseTest
import com.lofinif.sharedtest.foodEntityMock
import org.junit.Test

class FoodEntityToFoodDetailsModelMapperTest: BaseTest() {

    private lateinit var mapper: FoodEntityToFoodDetailsModelMapper
    private val dto = foodEntityMock

    override fun setUp() {
        super.setUp()
        mapper = FoodEntityToFoodDetailsModelMapper()
    }

    @Test
    fun `id is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.id).isEqualTo(dto.id)
    }
    @Test
    fun `name is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.name).isEqualTo(dto.name)
    }
    @Test
    fun `imageUrl is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.image).isEqualTo(dto.imageUrl)
    }
    @Test
    fun `description is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result.description).isEqualTo(dto.description)
    }

}