package com.abdulqohar.qmovieaddict.data.repository

import android.util.Log
import com.abdulqohar.qmovieaddict.data.local.dao.MovieDao
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.datasource.MovieRemoteDatasource
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class QMovieRepositoryImpl @Inject constructor(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieDao: MovieDao
): QMovieRepository {

    override fun getPopularMovies(): Flowable<List<MovieEntity>>  {
        val remoteMoviesSingle = movieRemoteDatasource.getPopularMovies().doOnSuccess { movies ->
            movieDao.insertMovies(movies)
        }.onErrorResumeNext { throwable ->
           Single.just(mutableListOf())
        }.toFlowable()

        val localMoviesSingle = movieDao.getAllMovies()
            .flatMap { cachedMovies ->
                if (cachedMovies.isNotEmpty()) {
                    Flowable.just(cachedMovies)
                } else {
                    Flowable.just(emptyList())
                }
            }

        return Flowable.merge(remoteMoviesSingle, localMoviesSingle).filter {
            movies -> movies.isNotEmpty()
        }
    }

    override fun getPopularMoviesDto(): Flowable<GetPopularMoviesDto> = movieRemoteDatasource.getPopularMoviesDto().toFlowable()
    override fun getMovieDetails(movieId: String): Flowable<MovieDetailsDto> =
        movieRemoteDatasource.getMovieDetails(movieId).toFlowable()

    override fun toggleFavouredState(movieId: String) {
        movieDao.toggleFavouredState(movieId.toInt())
    }

    override fun getFavouredState(movieId: String): Flowable<Boolean> =
        movieDao.getFavouredState(movieId.toInt())


    override fun getAllFavouredMovies(): Flowable<List<FavouredMovie>> =
        movieDao.getAllFavouredMovies()

    override fun saveFavouredMovie(favouredMovie: FavouredMovie) {
        movieDao.insertFavouredMovie(favouredMovie)

    }

}