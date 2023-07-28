package com.abdulqohar.qmovieaddict.data.remote.datasource

import android.util.Log
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import com.abdulqohar.qmovieaddict.data.remote.service.MovieDbService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRemoteDatasourceImpl @Inject constructor(private val movieDbService: MovieDbService) :
    MovieRemoteDatasource {

    override fun getPopularMovies(): Single<MutableList<MovieEntity>> {

        return movieDbService.getPopularMovies().map { response ->

            response.results?.map { movie ->
                MovieEntity(
                    adult = movie.adult,
                    backdropPath = movie.backdrop_path,
                    genreIds = movie.genre_ids,
                    id = movie.id,
                    originalLanguage = movie.original_language,
                    overview = movie.overview,
                    originalTitle = movie.original_title,
                    popularity = movie.popularity,
                    posterPath = movie.poster_path,
                    releaseDate = movie.release_date,
                    title = movie.title,
                    video = movie.video,
                    voteAverage = movie.vote_average,
                    voteCount = movie.vote_count
                )
            } as MutableList<MovieEntity>
        }
    }



    override fun getPopularMoviesDto(): Single<GetPopularMoviesDto> = movieDbService.getPopularMovies()
    override fun getMovieDetails(movieId: String): Single<MovieDetailsDto> =
        movieDbService.getMovieDetails(movieId)
}