package com.abdulqohar.qmovieaddict.util

import androidx.room.TypeConverter

class QMovieAddictTypeConverter {

    @TypeConverter
    fun fromListToString(genreIds: List<Int>?): String? {
        return genreIds?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(genreIdsString: String?): List<Int>? {
        return genreIdsString?.split(",")?.mapNotNull { it.toIntOrNull() }
    }

}