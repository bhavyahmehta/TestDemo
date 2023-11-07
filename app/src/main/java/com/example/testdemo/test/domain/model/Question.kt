package com.example.testdemo.test.domain.model

data class Question(
    val id: String,
    val question: String,
    val options: List<Option>,
)