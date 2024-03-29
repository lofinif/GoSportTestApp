package com.lofinif.gosporttestapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.lofinif.BaseTest
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.sharedtest.listAdsMock
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Test

class GetAdsUseCaseTest: BaseTest() {

    @MockK
    private lateinit var repo: MenuRepository

    private lateinit var useCase: GetAdsUseCase

    override fun setUp() {
        super.setUp()
        useCase = GetAdsUseCase(repo)
    }

    @Test
    fun `useCase return correct list`(){
        every { repo.listAds }.returns(listAdsMock)
        val result = useCase.listAdsPoster
        assertThat(result).isEqualTo(listAdsMock)
    }

}