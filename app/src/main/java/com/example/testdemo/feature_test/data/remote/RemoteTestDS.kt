package com.example.testdemo.feature_test.data.remote

import com.example.testdemo.feature_test.data.remote.model.RemoteQuestion
import javax.inject.Inject

class RemoteTestDS @Inject constructor(private val testApi: TestApi){

    suspend fun getRemoteTestData(): List<RemoteQuestion> {
        return testApi.getTestData()
    }
}