package com.example.f8d.data.entity

import java.io.Serializable

data class Food(
    var yemek_id: Int,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: Int
) : Serializable
