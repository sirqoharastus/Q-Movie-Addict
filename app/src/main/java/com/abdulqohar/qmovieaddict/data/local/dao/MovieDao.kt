package com.abdulqohar.qmovieaddict.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("UPDATE movie_table SET favoured = NOT favoured WHERE id = :movieId")
    fun toggleFavouredState(movieId: Int)

    @Query("SELECT favoured FROM movie_table WHERE id = :movieId")
    fun getFavouredState(movieId: Int): Flowable<Boolean>

    @Query("SELECT * FROM favoured_movies")
    fun getAllFavouredMovies(): Flowable<List<FavouredMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouredMovie(movie: FavouredMovie)
}