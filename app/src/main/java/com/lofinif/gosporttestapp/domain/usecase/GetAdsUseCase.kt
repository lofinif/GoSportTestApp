package com.lofinif.gosporttestapp.domain.usecase

import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import javax.inject.Inject

class GetAdsUseCase @Inject constructor(private val repo: MenuRepository) {
    val listAdsPoster
        get() = repo.listAds
}