package com.abdulqohar.qmovieaddict.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdulqohar.qmovieaddict.data.local.dao.MovieDao
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.util.QMovieAddictTypeConverter

@Database(entities = [MovieEntity::class, FavouredMovie::class], version = 2)
@TypeConverters(QMovieAddictTypeConverter::class)
abstract class QMovieAddictDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}