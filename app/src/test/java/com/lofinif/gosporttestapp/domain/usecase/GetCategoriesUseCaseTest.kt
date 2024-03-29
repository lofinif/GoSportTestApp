package com.lofinif.gosporttestapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.data.CallResult
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.sharedtest.categoriesMock
import com.lofinif.sharedtest.categoryListMock
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCategoriesUseCaseTest: BaseTest() {

    @MockK
    private lateinit var repo: MenuRepository

    private lateinit var useCase: GetCategoriesUseCase

    override fun setUp() {
        super.setUp()
        useCase = GetCategoriesUseCase(repo)
    }

    @Test
    fun `UseCase returns correct data`() {
        coEvery { repo.getCategories() }.returns(categoryListMock)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase()
            assertThat(result).isEqualTo(
                categoryListMock
            )
        }
    }
}