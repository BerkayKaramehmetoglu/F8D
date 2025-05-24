package com.example.f8d.data.service

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getServicesAPI(): ServicesAPI {
            return RetrofitClient.getClient(BASE_URL).create(ServicesAPI::class.java)
        }
    }
}