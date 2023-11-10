package com.example.testdemo.feature_test.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.testdemo.feature_test.data.local.model.LocalQuestion
import com.example.testdemo.feature_test.data.local.model.LocalQuestionWithOptions

@Dao
interface QuestionDao {
    @Query("SELECT * FROM Question")
    fun getAll(): List<LocalQuestion>

    @Query("SELECT *  FROM Question WHERE questionId = :id")
    suspend fun getQuestionById(id: Int): LocalQuestion?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: LocalQuestion): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<LocalQuestion>)

    @Query("Delete FROM Question")
    suspend fun deleteAll()

    @Delete
    fun deleteQuestion(question: LocalQuestion)

    @Transaction
    @Query("SELECT * FROM Question")
    suspend fun getQuestionWithOptions(): List<LocalQuestionWithOptions>
}