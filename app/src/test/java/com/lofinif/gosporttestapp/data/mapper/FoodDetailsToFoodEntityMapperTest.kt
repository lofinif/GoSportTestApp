package com.lofinif.gosporttestapp.data.mapper

import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.data.local.FoodDetailsToFoodEntityMapper
import com.lofinif.sharedtest.foodDetailsMock
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FoodDetailsToFoodEntityMapperTest: BaseTest() {

    private lateinit var mapper: FoodDetailsToFoodEntityMapper
    private val dto = foodDetailsMock

    override fun setUp() {
        super.setUp()
        mapper = FoodDetailsToFoodEntityMapper()
    }

    @Test
    fun `id is mapped correctly`(){
        val result = mapper.map(dto)
        assertThat(result.id).isEqualTo(dto.idMeal)
    }
    @Test
    fun `name is mapped correctly`(){
        val result = mapper.map(dto)
        assertThat(result.name).isEqualTo(dto.strMeal)
    }
    @Test
    fun `imageUrl is mapped correctly`(){
        val result = mapper.map(dto)
        assertThat(result.imageUrl).isEqualTo(dto.strMealThumb)
    }
    @Test
    fun `description is mapped correctly`(){
        val result = mapper.map(dto)
        assertThat(result.description).isEqualTo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20")
    }

}