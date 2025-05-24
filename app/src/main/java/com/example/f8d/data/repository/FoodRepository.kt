package com.example.f8d.data.repository

import com.example.f8d.data.datasource.FoodDataSource
import com.example.f8d.data.entity.Food

class FoodRepository(var foodDataSource: FoodDataSource){

    suspend fun getFood(): List<Food> = foodDataSource.getFood()
}