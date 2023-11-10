package com.example.testdemo.core.di

import android.content.Context
import androidx.room.Room
import com.example.testdemo.core.db.TestDatabase
import com.example.testdemo.core.db.TestDatabase.Companion.DATABASE_NAME
import com.example.testdemo.feature_test.data.TestRepoImpl
import com.example.testdemo.feature_test.data.local.LocalTestDS
import com.example.testdemo.feature_test.data.remote.RemoteTestDS
import com.example.testdemo.feature_test.data.remote.TestApi
import com.example.testdemo.feature_test.domain.repo.TestRepo
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

    @Provides
    @Singleton
    fun providesTodoRepo(localTestDS: LocalTestDS,remoteTestDS: RemoteTestDS): TestRepo {
        return TestRepoImpl(localTestDS, remoteTestDS)
    }
}