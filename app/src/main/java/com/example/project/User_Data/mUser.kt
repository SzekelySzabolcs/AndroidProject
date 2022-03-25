package com.example.project.User_Data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class mUser {
    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("data")
    @Expose
    private var data: List<UserData>?= null

    @SerializedName("timestamp")
    @Expose
    private var timestamp: Long? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getData(): List<UserData?>? {
        return data
    }

    fun setData(data: List<UserData>) {
        this.data = data
    }

    fun getTimestamp(): Long? {
        return timestamp
    }

    fun setTimestamp(timestamp: Long?) {
        this.timestamp = timestamp
    }
}