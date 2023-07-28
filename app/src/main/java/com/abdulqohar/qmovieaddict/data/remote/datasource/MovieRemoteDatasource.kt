package com.abdulqohar.qmovieaddict.data.remote.datasource

import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import io.reactivex.rxjava3.core.Single

interface MovieRemoteDatasource {
    fun getPopularMovies(): Single<MutableList<MovieEntity>>

    fun getPopularMoviesDto(): Single<GetPopularMoviesDto>

    fun getMovieDetails(movieId: String): Single<MovieDetailsDto>
}