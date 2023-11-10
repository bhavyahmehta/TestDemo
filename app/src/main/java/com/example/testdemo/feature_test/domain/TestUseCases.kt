package com.example.testdemo.feature_test.domain

import com.example.testdemo.feature_test.domain.model.QuestionWithOptions
import com.example.testdemo.feature_test.domain.repo.TestRepo
import javax.inject.Inject

class TestUseCases @Inject constructor(private val testRepo: TestRepo) {
    suspend fun getTestData(): List<QuestionWithOptions> {
        return testRepo.getTestData()
    }
}