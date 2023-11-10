package com.example.testdemo.feature_test.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testdemo.feature_test.data.local.model.LocalOption

@Dao
interface OptionDao {
    @Query("SELECT * FROM Option")
    fun getAll(): List<LocalOption>

    @Query("SELECT *  FROM Option WHERE optionId = :id")
    suspend fun getOptionById(id: Int): LocalOption?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(option: LocalOption): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(options: List<LocalOption>)

    @Query("Delete FROM Option")
    suspend fun deleteAll()

    @Delete
    fun deleteOption(option: LocalOption)
}