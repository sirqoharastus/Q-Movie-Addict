package com.abdulqohar.qmovieaddict.data.repository

import com.abdulqohar.qmovieaddict.data.local.dao.MovieDao
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.datasource.MovieRemoteDatasource
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class QMovieRepositoryImplTest {

    @Mock
    private lateinit var movieRemoteDatasource: MovieRemoteDatasource

    @Mock
    private lateinit var movieDao: MovieDao

    private lateinit var repository: QMovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = QMovieRepositoryImpl(movieRemoteDatasource, movieDao)
    }


    @Test
    fun `test getPopularMovies when remote data is not available and local data is available`() {
        // Arrange
        val localMovies =  listOf(MovieEntity(true, "Movie 1", listOf(), 1), MovieEntity(false, "Movie 2", listOf(), 2))


        `when`(movieRemoteDatasource.getPopularMovies()).thenReturn(Single.error(Throwable()))
        `when`(movieDao.getAllMovies()).thenReturn(Flowable.just(localMovies))

        // Act
        val testObserver = repository.getPopularMovies().test()

        // Assert
        testObserver.assertValue(localMovies)
        testObserver.assertComplete()
    }

    @Test
    fun `test getPopularMovies when neither remote data nor local data is available`() {
        // Arrange
        `when`(movieRemoteDatasource.getPopularMovies()).thenReturn(Single.error(Throwable()))
        `when`(movieDao.getAllMovies()).thenReturn(Flowable.just(emptyList()))

        // Act
        val testObserver = repository.getPopularMovies().test()

        // Assert
        testObserver.assertNoValues()
        testObserver.assertComplete()
    }

}