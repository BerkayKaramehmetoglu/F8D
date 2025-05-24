package com.example.f8d.data.service

import com.example.f8d.data.entity.FoodResponse
import retrofit2.http.GET

interface ServicesAPI {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFood(): FoodResponse

}
