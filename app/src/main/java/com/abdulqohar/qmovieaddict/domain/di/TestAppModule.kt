package com.abdulqohar.qmovieaddict.domain.di

import android.content.Context
import androidx.room.Room
import com.abdulqohar.qmovieaddict.data.local.database.QMovieAddictDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Named("q_test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, QMovieAddictDatabase::class.java)
            .allowMainThreadQueries().build()
}