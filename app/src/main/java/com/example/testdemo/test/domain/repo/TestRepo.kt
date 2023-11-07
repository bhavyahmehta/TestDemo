package com.example.testdemo.test.domain.repo

import com.example.testdemo.test.domain.model.Question

interface TestRepo {
    suspend fun getTestData(): List<Question>
}