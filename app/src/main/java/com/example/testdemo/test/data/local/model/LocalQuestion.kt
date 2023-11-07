package com.example.testdemo.test.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "Question")
data class LocalQuestion(
    @PrimaryKey
    val id: String,
    val question: String,
    @Relation(parentColumn = "id", entityColumn = "questionId")
    val options: List<LocalOption>,
)