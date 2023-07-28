package com.abdulqohar.qmovieaddict.domain.usecase

import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: QMovieRepository
) {
    operator fun invoke(): Flowable<List<MovieEntity>> = repository.getPopularMovies()
}