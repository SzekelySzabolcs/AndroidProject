package com.example.project

import androidx.lifecycle.ViewModel
import com.example.project.Products.product_class

class Modell:ViewModel() {
    var name = ""
    var email = ""
    var phone_number = ""
    var token = ""
    var creation_time = ""
    var refresh_time = ""
    var password=""
    val result1: ArrayList<product_class> = ArrayList()
    val myMarket: ArrayList<product_class> = ArrayList()
}