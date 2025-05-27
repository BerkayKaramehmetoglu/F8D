package com.example.f8d.data.datasource

import com.example.f8d.data.entity.CartResponse
import com.example.f8d.data.entity.Food
import com.example.f8d.data.service.ServicesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodAPI: ServicesAPI) {

    suspend fun getFood(): List<Food> =
        withContext(Dispatchers.IO) {
            return@withContext foodAPI.getFood().yemekler
        }

    suspend fun addCart(
        id: Int,
        title: String,
        imageTitle: String,
        price: Int,
        count: Int,
        username: String
    ) =
        withContext(Dispatchers.IO) {
            return@withContext foodAPI.addCart(id, title, imageTitle, price, count, username)
        }

    suspend fun getCart(username: String): CartResponse? =
        withContext(Dispatchers.IO) {
            return@withContext foodAPI.getCart(username).body()
        }

    suspend fun deleteCart(id: Int, username: String) =
        withContext(Dispatchers.IO){
            return@withContext foodAPI.deleteCart(id, username)
        }
}