package com.example.testdemo.test.data.remote

import com.example.testdemo.test.data.remote.model.RemoteQuestion
import javax.inject.Inject

class RemoteTestDS @Inject constructor(private val testApi: TestApi){

    suspend fun getTestData(): List<RemoteQuestion> {
        return testApi.getTestData()
    }
}