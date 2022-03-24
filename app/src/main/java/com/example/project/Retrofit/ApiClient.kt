package com.example.project.Retrofit



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiClient {

    val BASE_URL = "https://pure-gorge-51703.herokuapp.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    }