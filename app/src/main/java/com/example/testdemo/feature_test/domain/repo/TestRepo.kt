package com.example.testdemo.feature_test.domain.repo

import com.example.testdemo.feature_test.domain.model.QuestionWithOptions

interface TestRepo {
    suspend fun getTestData(): List<QuestionWithOptions>
}