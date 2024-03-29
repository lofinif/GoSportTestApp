package com.lofinif.gosporttestapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lofinif.gosporttestapp.data.local.AppDatabase
import com.lofinif.gosporttestapp.data.local.FoodDetailsToFoodEntityMapper
import com.lofinif.gosporttestapp.data.local.FoodEntity
import com.lofinif.gosporttestapp.data.menu.MenuRepositoryImpl
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.data.menu.dto.FoodDetails
import com.lofinif.gosporttestapp.data.network.ApiService
import com.lofinif.gosporttestapp.domain.repository.MenuRepository
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.mapper.CategoryToCategoryModelMapper
import com.lofinif.gosporttestapp.ui.menu.mapper.FoodEntityToFoodDetailsModelMapper
import com.lofinif.gosporttestapp.ui.menu.mapper.StringToCategoryModelMapper
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Suppress("JSON_FORMAT_REDUNDANT")
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        val convectorFactory = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }.asConverterFactory("application/json".toMediaType())
        return Retrofit.Builder()
            .baseUrl("https://themealdb.com")
            .addConverterFactory(convectorFactory)
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): ApiService = ApiService.create(retrofit)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMenuRepo(menuRepository: MenuRepositoryImpl): MenuRepository
}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MapperModule{
    @Binds
    abstract fun provideCategoryToCategoryModelMapper(mapper: CategoryToCategoryModelMapper): BaseMapper<Category, CategoryModel>
    @Binds
    abstract fun provideStringToCategoryModelMapper(mapper: StringToCategoryModelMapper): BaseMapper<String, Category>
    @Binds
    abstract fun provideFoodEntityToFoodDetailsModelMapper(mapper: FoodEntityToFoodDetailsModelMapper): BaseMapper<FoodEntity, FoodDetailsModel>
    @Binds
    abstract fun provideFoodDetailsToFoodEntityModelMapper(mapper: FoodDetailsToFoodEntityMapper): BaseMapper<FoodDetails, FoodEntity>
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule(){

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "app_database"
    ).build()

    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()
}

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    }
}