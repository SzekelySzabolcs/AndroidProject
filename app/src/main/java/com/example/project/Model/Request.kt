package com.example.project.Model

import com.google.gson.annotations.SerializedName



data class LoginRequest (
@SerializedName("username"        ) var mUserName      : String?    = null,
@SerializedName("password"        ) var mPassword      : String?    = null
)

data class RegisterRequest (

    @SerializedName("username"      ) var mUserName      : String?    = null,
    @SerializedName("password"      ) var mPassword      : String?    = null,
    @SerializedName("email"         ) var mEmail         : String?    = null,
    @SerializedName("phone_number"  ) var mPhoneNumber   : String?    = null
)


