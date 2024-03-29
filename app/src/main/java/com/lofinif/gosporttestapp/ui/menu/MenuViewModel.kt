package com.lofinif.gosporttestapp.ui.menu

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lofinif.gosporttestapp.data.CallResult
import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.domain.usecase.GetAdsUseCase
import com.lofinif.gosporttestapp.domain.usecase.GetCategoriesUseCase
import com.lofinif.gosporttestapp.domain.usecase.GetFoodDetailsUseCase
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel
import com.lofinif.gosporttestapp.ui.menu.state.MenuCategoryScreenState
import com.lofinif.gosporttestapp.ui.menu.state.MenuFoodScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getFoodListUseCase: GetFoodDetailsUseCase,
    private val mapperEntity: BaseMapper<FoodEntity, FoodDetailsModel>,
    private val mapperCategory: BaseMapper<Category, CategoryModel>,
) : ViewModel() {
    companion object{
        const val TAG = "MenuViewModel"
    }

    private val _screenStateFoodLiveData = MutableLiveData<MenuFoodScreenState>()
    val screenStateFoodLiveData: LiveData<MenuFoodScreenState> = _screenStateFoodLiveData

    private val _screenStateCategoryLiveData = MutableLiveData<MenuCategoryScreenState>()
    val screenStateCategoryLiveData: LiveData<MenuCategoryScreenState> = _screenStateCategoryLiveData

    private val _postersFlow = MutableStateFlow(getAdsUseCase.listAdsPoster)
    val postersFlow = _postersFlow.asStateFlow()

    @SuppressLint("CommitPrefEdits")
    fun fetchCategory(){
        _screenStateCategoryLiveData.value = MenuCategoryScreenState.Loading
        viewModelScope.launch {
            val response = getCategoriesUseCase.invoke().map{ mapperCategory.map(it) }
            if(response.isNotEmpty()){
                _screenStateCategoryLiveData.value = MenuCategoryScreenState.Loaded(response)
            } else {
                _screenStateCategoryLiveData.value = MenuCategoryScreenState.Error
            }
        }
    }

    fun fetchFood(category: String){
        viewModelScope.launch {
            _screenStateFoodLiveData.value = MenuFoodScreenState.Loading
            val response = getFoodListUseCase.invoke(category).map(mapperEntity::map)
            if (response.isNotEmpty()){
                _screenStateFoodLiveData.value = MenuFoodScreenState.Loaded(response)
            } else {
                _screenStateFoodLiveData.value = MenuFoodScreenState.Error
                Log.e(TAG, "Couldn't reach server")
                }
            }
        }
    }