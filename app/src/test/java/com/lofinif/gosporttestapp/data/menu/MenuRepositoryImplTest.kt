package com.lofinif.gosporttestapp.data.menu

import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.data.CallResult
import com.lofinif.gosporttestapp.data.local.FoodDao
import com.lofinif.gosporttestapp.data.local.FoodDetailsToFoodEntityMapper
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.data.network.ApiService
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.gosporttestapp.ui.menu.mapper.StringToCategoryModelMapper
import com.lofinif.sharedtest.categoriesMock
import com.lofinif.sharedtest.categoryListMock
import com.lofinif.sharedtest.foodDetailsMock
import com.lofinif.sharedtest.foodEntityListMock
import com.lofinif.sharedtest.foodEntityMock
import com.lofinif.sharedtest.foodListMock
import com.lofinif.sharedtest.foodsDetailsMock
import com.lofinif.sharedtest.listAdsMock
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MenuRepositoryImplTest: BaseTest() {

    @MockK
    private lateinit var apiService: ApiService
    @RelaxedMockK
    private lateinit var dao: FoodDao
    @MockK
    private lateinit var entityMapper: FoodDetailsToFoodEntityMapper
    @MockK
    private lateinit var mapperShared: StringToCategoryModelMapper
    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var repo: MenuRepository

    override fun setUp() {
        super.setUp()
        every { entityMapper.map(any()) }.returns(foodEntityMock)
        repo = MenuRepositoryImpl(apiService, dao, entityMapper, sharedPreferences, mapperShared)
    }

    @Test
    fun `getListFood return data from remote`() {
        coEvery { apiService.getListFoods(any()) }.returns(foodListMock)
        coEvery { apiService.getDetailsFood(any()) }.returns(foodsDetailsMock)
        runTest {
            val result = repo.getDetailsFood("")
            assertThat(result).isEqualTo(foodEntityListMock)
        }
    }
    @Test
    fun `getListFood return data from local`() {
        coEvery { apiService.getListFoods(any()) }.throws(HttpException(Response.error<Nothing>(400,"".toResponseBody())))
        coEvery { apiService.getDetailsFood(any()) }.throws(HttpException(Response.error<Nothing>(400,"".toResponseBody())))
        coEvery { dao.getFoodsByCategory(any()) }.returns(foodEntityListMock)
        runTest {
            val result = repo.getDetailsFood("")
            assertThat(result).isEqualTo(foodEntityListMock)
        }
    }
}