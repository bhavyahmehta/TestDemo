package com.example.testdemo.test.data.local

import com.example.testdemo.test.data.local.model.LocalQuestion
import javax.inject.Inject

class LocalTestDS @Inject constructor(private val db:TestDatabase) {

    private val questionDao = db.questionDao
    private val optionDao = db.optionDao

    suspend fun insertQuestionsAndOptions(localQuestions:List<LocalQuestion>){
        questionDao.insertAll(localQuestions)
    }

    suspend fun getQuestionsAndOptions(): List<LocalQuestion> {
        return questionDao.getAll()
    }
}