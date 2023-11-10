package com.example.testdemo.feature_test.domain.model

data class QuestionWithOptions(
    val question: Question,
    val options:List<Option>
)