package com.lofinif.gosporttestapp.ui.menu.mapper

import com.google.common.truth.Truth
import com.lofinif.BaseTest
import com.lofinif.sharedtest.categoryMock
import com.lofinif.sharedtest.stringSharedPrefMock
import org.junit.Test

class StringToCategoryModelMapperTest: BaseTest() {

    private lateinit var mapper: StringToCategoryModelMapper
    private val dto = stringSharedPrefMock

    override fun setUp() {
        super.setUp()
        mapper = StringToCategoryModelMapper()
    }

    @Test
    fun `category is mapped correctly`(){
        val result = mapper.map(dto)
        Truth.assertThat(result).isEqualTo(categoryMock)
    }
}