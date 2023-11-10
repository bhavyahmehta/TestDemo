package com.example.testdemo.feature_test.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Option", foreignKeys = [ForeignKey(entity = LocalQuestion::class,
    parentColumns = ["questionId"],
    childColumns = ["questionId"],
    onDelete = CASCADE)])
data class LocalOption(
    @PrimaryKey
    val optionId: String,
    val option: String,
    val questionId: String,
)