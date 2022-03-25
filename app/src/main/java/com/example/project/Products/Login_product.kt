package com.example.project.Products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login_product (){
    @SerializedName("item_count")
    @Expose
    private var itemCount: Int? = null

    @SerializedName("products")
    @Expose
    private var products: List<Product?>? = null

    @SerializedName("timestamp")
    @Expose
    private var timestamp: Long? = null

    fun getItemCount(): Int? {
        return itemCount
    }

    fun setItemCount(itemCount: Int?) {
        this.itemCount = itemCount
    }

    fun getProducts(): List<Product?>? {
        return products
    }

    fun setProducts(products: List<Product?>?) {
        this.products = products
    }

    fun getTimestamp(): Long? {
        return timestamp
    }

    fun setTimestamp(timestamp: Long?) {
        this.timestamp = timestamp
    }
}