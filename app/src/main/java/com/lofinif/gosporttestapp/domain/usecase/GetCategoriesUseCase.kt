package com.lofinif.gosporttestapp.domain.usecase

import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repo: MenuRepository){
    suspend operator fun invoke(): List<Category> = repo.getCategories()
}