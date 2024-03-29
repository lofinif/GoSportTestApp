package com.lofinif.gosporttestapp.domain.usecase

import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFoodDetailsUseCase @Inject constructor(private val repo: MenuRepository) {
    suspend operator fun invoke(category: String) = withContext(Dispatchers.IO) { repo.getDetailsFood(category) }
}