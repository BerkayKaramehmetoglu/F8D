package com.example.f8d.data.service

import com.example.f8d.data.entity.CRDResponse
import com.example.f8d.data.entity.CartResponse
import com.example.f8d.data.entity.Food
import com.example.f8d.data.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicesAPI {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFood(): FoodResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addCart(
        @Field("sepet_yemek_id") id: Int,
        @Field("yemek_adi") title: String,
        @Field("yemek_resim_adi") imageTitle: String,
        @Field("yemek_fiyat") price: Int,
        @Field("yemek_siparis_adet") count: Int,
        @Field("kullanici_adi") username: String
    ): CRDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCart(@Field("kullanici_adi") username: String): CartResponse
}
