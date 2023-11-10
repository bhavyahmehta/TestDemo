package com.example.testdemo.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testdemo.feature_test.data.local.dao.OptionDao
import com.example.testdemo.feature_test.data.local.dao.QuestionDao
import com.example.testdemo.feature_test.data.local.model.LocalOption
import com.example.testdemo.feature_test.data.local.model.LocalQuestion

@Database(
    entities = [LocalQuestion::class, LocalOption::class],
    version = 1,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {

    abstract val questionDao: QuestionDao
    abstract val optionDao: OptionDao

    companion object {
        const val DATABASE_NAME = "test_db"
    }
}