package com.example.project.User_Data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UpdateUser {
    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("updatedData")
    @Expose
    private var updatedData: UpdatedData? = null

    @SerializedName("timestamp")
    @Expose
    private var timestamp: Long? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getUpdatedData(): UpdatedData? {
        return updatedData
    }

    fun setUpdatedData(updatedData: UpdatedData?) {
        this.updatedData = updatedData
    }

    fun getTimestamp(): Long? {
        return timestamp
    }

    fun setTimestamp(timestamp: Long?) {
        this.timestamp = timestamp
    }
}