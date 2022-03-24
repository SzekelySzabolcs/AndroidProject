package com.example.project.Retrofit

import com.example.project.Model.LoginRequest
import com.example.project.Model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface RetroService {

    @POST("/user/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

}