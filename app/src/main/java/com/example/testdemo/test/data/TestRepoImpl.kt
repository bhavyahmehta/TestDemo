package com.example.testdemo.test.data

import com.example.testdemo.test.data.local.LocalTestDS
import com.example.testdemo.test.data.remote.RemoteTestDS
import com.example.testdemo.test.domain.model.Question
import com.example.testdemo.test.domain.repo.TestRepo
import javax.inject.Inject

class TestRepoImpl @Inject constructor(
    private val localDS: LocalTestDS,
    private val remoteDS: RemoteTestDS,
): TestRepo {
    override suspend fun getTestData(): List<Question> {
        val remoteTestData = remoteDS.getTestData()
        localDS.insertQuestionsAndOptions(remoteTestData.toLocalListFromRemote())
        return localDS.getQuestionsAndOptions().toQuestionListFromLocal()
    }
}