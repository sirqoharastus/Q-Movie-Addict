package com.abdulqohar.qmovieaddict.domain.di

import android.content.Context
import androidx.room.Room
import com.abdulqohar.qmovieaddict.data.local.dao.MovieDao
import com.abdulqohar.qmovieaddict.data.local.database.QMovieAddictDatabase
import com.abdulqohar.qmovieaddict.data.remote.datasource.MovieRemoteDatasource
import com.abdulqohar.qmovieaddict.data.remote.datasource.MovieRemoteDatasourceImpl
import com.abdulqohar.qmovieaddict.data.remote.service.MovieDbService
import com.abdulqohar.qmovieaddict.data.repository.QMovieRepositoryImpl
import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import com.abdulqohar.qmovieaddict.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesMovieDbService(retrofit: Retrofit): MovieDbService =
        retrofit.create(MovieDbService::class.java)

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieDbService: MovieDbService): MovieRemoteDatasource {
        return MovieRemoteDatasourceImpl(movieDbService)
    }

    @Provides
    @Singleton
    fun provideMovieDao(@ApplicationContext context: Context): MovieDao {
        return Room.databaseBuilder(context, QMovieAddictDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
            .movieDao()
    }


    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDatasource,
        movieDao: MovieDao
    ): QMovieRepository {
        return QMovieRepositoryImpl(movieRemoteDataSource, movieDao)
    }

    @Provides
    @Singleton
    fun providegetPopularMoviesUseCase(movieRepository: QMovieRepository): GetPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
}