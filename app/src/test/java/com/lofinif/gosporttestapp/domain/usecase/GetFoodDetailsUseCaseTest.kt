package com.lofinif.gosporttestapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.sharedtest.foodEntityListMock
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class GetFoodDetailsUseCaseTest: BaseTest() {

    @MockK
    private lateinit var repo: MenuRepository

    private lateinit var useCase: GetFoodDetailsUseCase

    override fun setUp() {
        super.setUp()
        useCase = GetFoodDetailsUseCase(repo)
    }

    @Test
    fun `Repo returns data`() {
        coEvery { repo.getDetailsFood(any()) }.returns(foodEntityListMock)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase("")
            assertThat(result).isEqualTo(foodEntityListMock)
        }
    }
}