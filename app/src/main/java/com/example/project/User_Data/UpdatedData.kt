package com.example.project.User_Data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UpdatedData {
    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("phone_number")
    @Expose
    private var phoneNumber: Int? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("firebase_token")
    @Expose
    private var firebaseToken: String? = null

    @SerializedName("is_activated")
    @Expose
    private var isActivated: Boolean? = null

    @SerializedName("creation_time")
    @Expose
    private var creationTime: String? = null

    @SerializedName("token")
    @Expose
    private var token: String? = null

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getPhoneNumber(): Int? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: Int?) {
        this.phoneNumber = phoneNumber
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getFirebaseToken(): String? {
        return firebaseToken
    }

    fun setFirebaseToken(firebaseToken: String?) {
        this.firebaseToken = firebaseToken
    }

    fun getIsActivated(): Boolean? {
        return isActivated
    }

    fun setIsActivated(isActivated: Boolean?) {
        this.isActivated = isActivated
    }

    fun getCreationTime(): String? {
        return creationTime
    }

    fun setCreationTime(creationTime: String?) {
        this.creationTime = creationTime
    }

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String?) {
        this.token = token
    }
}