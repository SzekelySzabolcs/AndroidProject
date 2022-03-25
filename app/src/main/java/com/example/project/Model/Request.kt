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

data class passwordForgot (

    @SerializedName("username"        ) var mUserName      : String?    = null,
    @SerializedName("email"        ) var mEmail      : String?    = null

)
data class activateRequest (

    @SerializedName("username"      ) var mUserName      : String?    = null

)
data class productAdd(
    @SerializedName("creation"            ) var creation             : String?     = null,
    @SerializedName("product_id"          ) var product_id           : String?     = null,
    @SerializedName("title"               ) var title                : String?     = null,
    @SerializedName("description"         ) var description          : String?     = null,
    @SerializedName("price_per_unit"      ) var price_per_unit       : String?     = null,
    @SerializedName("units"               ) var units                : String?     = null,
    @SerializedName("is_active"           ) var is_active            : Boolean?    = null,
    @SerializedName("rating"              ) var rating               : Double?     = null,
    @SerializedName("amount_type"         ) var amount_type          : String?     = null,
    @SerializedName("price_type"          ) var price_type           : String?     = null
)
data class UserUpdate (
    @SerializedName("token"               ) var token                : String?     = null,
    @SerializedName("phone_number"        ) var mPhoneNumber         : String?     = null,
    @SerializedName("email"               ) var mEmail               : String?     = null,
    @SerializedName("username"            ) var mUserName            : String?     = null

)

data class tokenRefresh(
    @SerializedName("token"               ) var token                : String?     = null
)

