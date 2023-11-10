package com.example.testdemo.feature_test.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class LocalQuestionWithOptions(
    @Embedded
    val question: LocalQuestion,
    @Relation(parentColumn = "questionId",
        entityColumn = "questionId")
    val options:List<LocalOption>
)
