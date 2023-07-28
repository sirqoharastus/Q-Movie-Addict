package com.abdulqohar.qmovieaddict.presentation.ui.mappers

import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity

class Mappers {
    companion object {
        //Function to convert movie entity for favourite item
        fun mapMovieEntityToFavouredEntity(movieEntity: MovieEntity): FavouredMovie =
            FavouredMovie(
                adult = movieEntity.adult,
                backdropPath = movieEntity.backdropPath,
                genreIds = movieEntity.genreIds,
                id = movieEntity.id,
                originalLanguage = movieEntity.originalLanguage,
                originalTitle = movieEntity.originalTitle,
                overview = movieEntity.overview,
                popularity = movieEntity.popularity,
                posterPath = movieEntity.posterPath,
                releaseDate = movieEntity.releaseDate,
                title = movieEntity.title,
                video = movieEntity.video,
                voteAverage = movieEntity.voteAverage,
                voteCount = movieEntity.voteCount,
                favoured = movieEntity.favoured
            )
        //Function to convert favourite entity for movie item
        fun mapFavouredMovieEntityToMovieEntity(favouredMovie: FavouredMovie) =
            MovieEntity(
                adult = favouredMovie.adult,
                backdropPath = favouredMovie.backdropPath,
                genreIds = favouredMovie.genreIds,
                id = favouredMovie.id,
                originalLanguage = favouredMovie.originalLanguage,
                originalTitle = favouredMovie.originalTitle,
                overview = favouredMovie.overview,
                popularity = favouredMovie.popularity,
                posterPath = favouredMovie.posterPath,
                releaseDate = favouredMovie.releaseDate,
                title = favouredMovie.title,
                video = favouredMovie.video,
                voteAverage = favouredMovie.voteAverage,
                voteCount = favouredMovie.voteCount,
                favoured = favouredMovie.favoured
            )
    }


}