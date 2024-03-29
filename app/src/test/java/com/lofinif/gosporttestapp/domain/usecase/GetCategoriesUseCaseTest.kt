package com.lofinif.gosporttestapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.data.CallResult
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.sharedtest.categoriesMock
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
    fun `Repo call returns success, use case also returns Success`() {
        coEvery { repo.getCategories() }.returns(CallResult.Success(categoriesMock))
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase()
            assertThat(result).isInstanceOf(CallResult.Success::class.java)
            assertThat((result as CallResult.Success).value).isEqualTo(
                categoriesMock
            )
        }
    }

    @Test
    fun `Repo call returns IOError, use case also returns IOError`() {
        coEvery { repo.getCategories() }.returns(CallResult.IOError)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase()
            assertThat(result).isInstanceOf(CallResult.IOError::class.java)
        }
    }

    @Test
    fun `Repo call returns HttpException, use case also returns HttpError`() {
        coEvery { repo.getCategories() }.returns(CallResult.HttpError(400, "msg"))
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase()
            assertThat(result).isInstanceOf(CallResult.HttpError::class.java)
            assertThat((result as CallResult.HttpError).code).isEqualTo(400)
            assertThat(result.message).isEqualTo("msg")
        }
    }

    @Test
    fun `Repo call returns UnknownError, use case also returns UnknownError`() {
        coEvery { repo.getCategories() }.returns(CallResult.UnknownError)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase()
            assertThat(result).isInstanceOf(CallResult.UnknownError::class.java)
        }
    }
}