package com.example.f8d.data.datasource

import com.example.f8d.data.entity.Food
import com.example.f8d.data.service.ServicesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodAPI: ServicesAPI) {

    suspend fun getFood(): List<Food> =
        withContext(Dispatchers.IO) {
            return@withContext foodAPI.getFood().yemekler
        }

}