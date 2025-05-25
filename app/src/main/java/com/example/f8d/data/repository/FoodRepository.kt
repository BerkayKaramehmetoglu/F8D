package com.example.f8d.data.repository

import com.example.f8d.data.datasource.FoodDataSource
import com.example.f8d.data.entity.CRDResponse
import com.example.f8d.data.entity.Cart
import com.example.f8d.data.entity.CartResponse
import com.example.f8d.data.entity.Food

class FoodRepository(var foodDataSource: FoodDataSource) {

    suspend fun getFood(): List<Food> = foodDataSource.getFood()

    suspend fun addCart(
        id: Int,
        title: String,
        imageTitle: String,
        price: Int,
        count: Int,
        username: String
    ) =
        foodDataSource.addCart(id, title, imageTitle, price, count, username)

    suspend fun getCart(username: String): CartResponse? = foodDataSource.getCart(username)

    suspend fun deleteCart(id: Int, username: String) = foodDataSource.deleteCart(id, username)

}