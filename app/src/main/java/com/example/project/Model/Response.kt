package com.example.project.Model

data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)
data class RegisterRespons(
    var username: String,
    var password:String,
    var email: String,
    var phone_number: Int
        )

