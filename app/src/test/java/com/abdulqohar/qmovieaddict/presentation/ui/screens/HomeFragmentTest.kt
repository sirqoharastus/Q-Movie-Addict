package com.abdulqohar.qmovieaddict.presentation.ui.screens
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.RecyclerView
//import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
//import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
//import com.abdulqohar.qmovieaddict.domain.usecase.GetFavouredMoviesUseCase
//import com.abdulqohar.qmovieaddict.domain.usecase.GetPopularMoviesDtoUseCase
//import com.abdulqohar.qmovieaddict.domain.usecase.GetPopularMoviesUseCase
//import com.abdulqohar.qmovieaddict.presentation.ui.viewmodel.HomeViewModel
//import io.mockk.MockKAnnotations
//import io.mockk.every
//import io.mockk.impl.annotations.MockK
//import io.mockk.verify
//import io.reactivex.rxjava3.core.Flowable
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class HomeFragmentTest {
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @MockK(relaxUnitFun = true)
//    lateinit var homeViewModel: HomeViewModel
//
//    @MockK(relaxUnitFun = true)
//    lateinit var mockRepository: QMovieRepository
//
//    @MockK(relaxUnitFun = true)
//    lateinit var mockGetPopularMoviesUseCase: GetPopularMoviesUseCase
//
//    @MockK(relaxUnitFun = true)
//    lateinit var mockGetPopularMoviesDtoUseCase: GetPopularMoviesDtoUseCase
//
//    @MockK(relaxUnitFun = true)
//    lateinit var mockGetFavouredMoviesUseCase: GetFavouredMoviesUseCase
//
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//        homeViewModel = HomeViewModel(mockGetPopularMoviesUseCase, mockGetPopularMoviesDtoUseCase, mockGetFavouredMoviesUseCase)
//
//    }
//
//
//
//    @Test
//    fun testFetchPopularMovies() {
//        // Prepare the mock data and observable
//        val movieList = listOf(
//            MovieEntity(id = 1, title = "Movie 1"),
//            MovieEntity(id = 2, title = "Movie 2"),
//            // Add more movies as needed...
//        )
//        val moviesFlowable = Flowable.just(movieList)
//
//        // Mock the repository method to return the mock data
//        every { mockRepository.getPopularMovies() } returns moviesFlowable
//
//        // Call the function to be tested
//        homeViewModel.fetchPopularMovies()
//
//        // Verify that the repository method is called
//        verify(exactly = 1) { mockRepository.getPopularMovies() }
//
//        // Verify that the LiveData in the ViewModel is updated with the expected data
//        homeViewModel.popularMovies.value?.let { result ->
//            assert(result == movieList)
//        }
//    }
//
//
//    // Write similar tests for other functionality and interactions in the fragment.
//    // For example, testing the FiltersAdapter, navigation, etc.
//}