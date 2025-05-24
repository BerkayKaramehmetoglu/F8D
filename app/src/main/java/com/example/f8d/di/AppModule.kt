package com.example.f8d.di

import com.example.f8d.data.datasource.FoodDataSource
import com.example.f8d.data.repository.FoodRepository
import com.example.f8d.data.service.ApiUtils
import com.example.f8d.data.service.ServicesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodDataSource(servicesApi: ServicesAPI): FoodDataSource {
        return FoodDataSource(servicesApi)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: FoodDataSource): FoodRepository {
        return FoodRepository(foodDataSource)
    }

    @Provides
    @Singleton
    fun provideServicesAPI(): ServicesAPI {
        return ApiUtils.getServicesAPI()
    }

}