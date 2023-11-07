package com.example.testdemo.test.data.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteQuestion (
    @SerializedName("id") var id:String? = null,
    @SerializedName("options") var options:ArrayList<RemoteOption> = arrayListOf(),
    @SerializedName("question") var question:String? = null
)