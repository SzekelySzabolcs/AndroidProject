package com.example.project.Products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {

    @SerializedName("rating")

    private var rating: Double? = null

    @SerializedName("amount_type")
    @Expose
    private var amountType: String? = null

    @SerializedName("price_type")
    @Expose
    private var priceType: String? = null

    @SerializedName("product_id")
    @Expose
    private var productId: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("is_active")
    @Expose
    private var isActive: Boolean? = null

    @SerializedName("price_per_unit")
    @Expose
    private var pricePerUnit: String? = null

    @SerializedName("units")
    @Expose
    private var units: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("images")
    @Expose
    private var images: List<Any?>? = null

    @SerializedName("creation_time")
    @Expose
    private var creationTime: Long? = null

    @SerializedName("messages")
    @Expose
    private var messages: List<Any?>? = null

    fun getRating(): Double? {
        return rating
    }

    fun setRating(rating: Double?) {
        this.rating = rating
    }

    fun getAmountType(): String? {
        return amountType
    }

    fun setAmountType(amountType: String?) {
        this.amountType = amountType
    }

    fun getPriceType(): String? {
        return priceType
    }

    fun setPriceType(priceType: String?) {
        this.priceType = priceType
    }

    fun getProductId(): String? {
        return productId
    }

    fun setProductId(productId: String?) {
        this.productId = productId
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getIsActive(): Boolean? {
        return isActive
    }

    fun setIsActive(isActive: Boolean?) {
        this.isActive = isActive
    }

    fun getPricePerUnit(): String? {
        return pricePerUnit
    }

    fun setPricePerUnit(pricePerUnit: String?) {
        this.pricePerUnit = pricePerUnit
    }

    fun getUnits(): String? {
        return units
    }

    fun setUnits(units: String?) {
        this.units = units
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getImages(): List<Any?>? {
        return images
    }

    fun setImages(images: List<Any?>?) {
        this.images = images
    }

    fun getCreationTime(): Long? {
        return creationTime
    }

    fun setCreationTime(creationTime: Long?) {
        this.creationTime = creationTime
    }

    fun getMessages(): List<Any?>? {
        return messages
    }

    fun setMessages(messages: List<Any?>?) {
        this.messages = messages
    }

}