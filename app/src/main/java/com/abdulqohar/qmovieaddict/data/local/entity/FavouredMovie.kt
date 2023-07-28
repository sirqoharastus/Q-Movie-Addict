package com.abdulqohar.qmovieaddict.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoured_movies")
data class FavouredMovie(
    val adult: Boolean? = false,
    val backdropPath: String? = "",
    val genreIds: List<Int>? = listOf(),
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val posterPath: String? = "",
    val releaseDate: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    val favoured: Boolean = false
)
