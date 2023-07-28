package com.abdulqohar.qmovieaddict.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class MovieEntity(
    val adult: Boolean?=false,
    val backdropPath: String? = "",
    val genreIds: List<Int>? = listOf(),
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalLanguage: String? ="",
    val originalTitle: String? ="",
    val overview: String? ="",
    val popularity: Double? = 0.0,
    val posterPath: String? ="",
    val releaseDate: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    val favoured: Boolean = false
): Parcelable
