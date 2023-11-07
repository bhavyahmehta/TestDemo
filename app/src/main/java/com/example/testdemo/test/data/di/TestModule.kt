package com.example.testdemo.test.data.di

import android.content.Context
import androidx.room.Room
import com.example.testdemo.test.data.local.TestDatabase
import com.example.testdemo.test.data.local.TestDatabase.Companion.DATABASE_NAME
import com.example.testdemo.test.data.remote.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://testdemo-3b552-default-rtdb.firebaseio.com/")
            .build()
    }

    @Provides
    fun providesTestApi(retrofit:Retrofit): TestApi {
        return retrofit.create(TestApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDB(@ApplicationContext appContext: Context): TestDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            TestDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}