package com.example.testdemo.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteOption(
    @SerializedName("id") var id:String? = null,
    @SerializedName("option") var option: String? = null,
)
