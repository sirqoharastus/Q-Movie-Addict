package com.abdulqohar.qmovieaddict.data.remote.service

import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import com.abdulqohar.qmovieaddict.util.Constants.Companion.MOVIE_DETAILS
import com.abdulqohar.qmovieaddict.util.Constants.Companion.POPULAR_MOVIES
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbService {

    @GET(POPULAR_MOVIES)
    fun getPopularMovies(): Single<GetPopularMoviesDto>

    @GET(MOVIE_DETAILS)
    fun getMovieDetails(@Path("movie_id") movieId: String): Single<MovieDetailsDto>

}