package com.example.project.Products

data class product_class(
    var rating: Int?, var amount_type:String, var price_type:String,
    var product_id:String, var username:String,
    var is_active:String, var price_per_unit:String, var units:String, var description:String,
    var title:String, var creation_time:String)



data class allItem(var item_count:String, var item: List<product_class>,var timestamp:String)


