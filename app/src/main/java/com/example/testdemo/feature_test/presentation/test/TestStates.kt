package com.example.testdemo.feature_test.presentation.test

import com.example.testdemo.feature_test.domain.model.QuestionWithOptions

data class TestStates(
    val error: String? = null,
    val isLoading: Boolean = true,
    val testData: List<QuestionWithOptions> = emptyList(),
)
