package com.abdulqohar.qmovieaddict.domain.repository

import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface QMovieRepository {
    fun getPopularMovies(): Flowable<List<MovieEntity>>
    fun getPopularMoviesDto(): Flowable<GetPopularMoviesDto>
    fun getMovieDetails(movieId: String): Flowable<MovieDetailsDto>
    fun toggleFavouredState(movieId: String)
    fun getFavouredState(movieId: String): Flowable<Boolean>
    fun getAllFavouredMovies(): Flowable<List<FavouredMovie>>
    fun saveFavouredMovie(favouredMovie: FavouredMovie)
}