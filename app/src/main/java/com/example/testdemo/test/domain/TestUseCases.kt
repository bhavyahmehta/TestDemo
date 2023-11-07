package com.example.testdemo.test.domain

import com.example.testdemo.test.domain.model.Question
import com.example.testdemo.test.domain.repo.TestRepo
import javax.inject.Inject

class TestUseCases @Inject constructor(private val testRepo: TestRepo) {
    suspend fun getTestData(): List<Question> {
        return testRepo.getTestData()
    }
}