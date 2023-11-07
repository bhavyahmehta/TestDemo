package com.example.testdemo.test.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testdemo.test.data.local.model.LocalQuestion

interface QuestionDao {
    @Query("SELECT * FROM Question")
    fun getAll(): List<LocalQuestion>

    @Query("SELECT *  FROM Question WHERE id = :id")
    suspend fun getQuestionById(id: Int): LocalQuestion?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: LocalQuestion): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<LocalQuestion>)

    @Query("Delete FROM Question")
    suspend fun deleteAll()

    @Delete
    fun deleteQuestion(question: LocalQuestion)
}