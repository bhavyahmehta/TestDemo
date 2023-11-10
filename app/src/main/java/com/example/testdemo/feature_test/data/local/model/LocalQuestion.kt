package com.example.testdemo.feature_test.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class LocalQuestion(
    @PrimaryKey
    val questionId: String,
    val question: String)