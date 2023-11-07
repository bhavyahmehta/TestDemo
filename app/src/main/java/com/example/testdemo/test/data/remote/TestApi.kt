package com.example.testdemo.test.data.remote

import com.example.testdemo.test.data.remote.model.RemoteQuestion
import retrofit2.http.GET

interface TestApi {
    @GET("data.json")
    suspend fun getTestData():List<RemoteQuestion>
}