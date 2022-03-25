package com.example.project.Retrofit

import com.example.project.Model.*
import com.example.project.Products.Login_product
import com.example.project.User_Data.mUser
import retrofit2.Call
import retrofit2.http.*


interface RetroService {

    @POST("/user/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @GET("/user/data")
    fun user(@Header("username")username: String?):Call<mUser>

    @GET("/user/refresh")
    fun tokenr(@Header("token")token: String?, @Header("username") username: String?):Call<tokenRefresh>


    @Multipart
    @POST("/products/add")
    fun AddProd(@Header ("token") token:String,
                @Part("title")title :String,
                @Part("description")description :String,
                @Part("price_per_unit")price_per_unit :String,
                @Part("units")units :String,
                @Part("is_active")is_active :Boolean,
                @Part("rating")rating :Double,
                @Part("amount_type")amount_type :String,
                @Part("price_type")price_type :String  ) :Call<productAdd>

    @GET("/products")
    fun Myproduct(@Header ("token") token:String,
                  @Header("filter") filter:String):Call<Login_product>

    @POST("products/remove?product_id=")
    fun delete(@Header ("token") token:String,
               @Query ("product_id") product_id:String, ):Call<Login_product>



    @POST("/user/update")
    fun UserUpdate(@Header ("token") token:String,
                   @Part("phone_number")phone_number :String,
                   @Part("email")email :String,
                   @Part("username")username :String):Call<UserUpdate>
}