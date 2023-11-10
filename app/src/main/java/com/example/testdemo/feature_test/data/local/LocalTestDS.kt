package com.example.testdemo.feature_test.data.local

import com.example.testdemo.core.db.TestDatabase
import com.example.testdemo.feature_test.data.local.model.LocalOption
import com.example.testdemo.feature_test.data.local.model.LocalQuestion
import com.example.testdemo.feature_test.data.local.model.LocalQuestionWithOptions
import javax.inject.Inject

class LocalTestDS @Inject constructor(db: TestDatabase) {

    private val questionDao = db.questionDao
    private val optionDao = db.optionDao

    suspend fun insertLocalQuestionData(localQuestions: List<LocalQuestion>) {
        questionDao.insertAll(localQuestions)
    }

    suspend fun insertLocalOptionsData(localOptions: List<LocalOption>) {
        optionDao.insertAll(localOptions)
    }

    suspend fun getLocalTestData(): List<LocalQuestionWithOptions> {
        return questionDao.getQuestionWithOptions()
    }
}