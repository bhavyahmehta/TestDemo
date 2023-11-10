package com.example.testdemo.feature_test.data

import android.util.Log
import com.example.testdemo.feature_test.data.local.LocalTestDS
import com.example.testdemo.feature_test.data.local.model.LocalOption
import com.example.testdemo.feature_test.data.local.model.LocalQuestion
import com.example.testdemo.feature_test.data.remote.RemoteTestDS
import com.example.testdemo.feature_test.domain.model.QuestionWithOptions
import com.example.testdemo.feature_test.domain.repo.TestRepo
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class TestRepoImpl @Inject constructor(
    private val localDS: LocalTestDS,
    private val remoteDS: RemoteTestDS,
) : TestRepo {

    override suspend fun getTestData(): List<QuestionWithOptions> {
        if (isCacheEmpty()) {
            try {
                val remoteTestData = remoteDS.getRemoteTestData()
                val questions = remoteTestData.map {LocalQuestion(
                    questionId = it.id?:"",
                    question = it.question?:""
                ) }
                val options = mutableListOf<LocalOption>()
                remoteTestData.forEach { testData ->
                    testData.options.forEach { remoteOption ->
                        options.add(LocalOption(
                            questionId = testData.id?:"",
                            option = remoteOption.option?:"",
                            optionId = remoteOption.id?:""
                        ))
                    }
                }
                localDS.insertLocalQuestionData(questions)
                localDS.insertLocalOptionsData(options)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from Remote")
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device offline and\nno data from local Room cache")
                        }
                    }

                    else -> throw e
                }
            }
        }
        return localDS.getLocalTestData().toQuestionWithOptionsListFromLocal()
    }

    private suspend fun isCacheEmpty(): Boolean {
        return localDS.getLocalTestData().isEmpty()
    }
}