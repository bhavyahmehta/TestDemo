package com.example.testdemo.test.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Option")
data class LocalOption(
    @PrimaryKey
    val id: String,
    val option: String,
    val questionId: String,
)