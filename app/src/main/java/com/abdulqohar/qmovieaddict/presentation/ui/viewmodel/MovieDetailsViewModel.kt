package com.abdulqohar.qmovieaddict.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import com.abdulqohar.qmovieaddict.domain.usecase.GetFavouredStateUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.GetMovieDetailsUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.SavedFavouredMovieUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.ToggleFavouredStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val toggleFavouredStateUseCase: ToggleFavouredStateUseCase,
    private val getFavouredState: GetFavouredStateUseCase,
    private val saveFavouredStateUseCase: SavedFavouredMovieUseCase
): ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetailsDto>()
    val popularMovies: LiveData<MovieDetailsDto> get() = _movieDetails

    private val _movieToggleState = MutableLiveData<Boolean>()
    val movieToggleState: LiveData<Boolean> get() = _movieToggleState



    private val disposable = CompositeDisposable()


    fun getMovieDetails(movieId: String) {
        disposable.add(
            getMovieDetailsUseCase(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { moviesDetails ->
                        _movieDetails.value = moviesDetails
                        Log.d("fetchPopularMoviesError", "$moviesDetails")
                    },
                    { error ->
                        Log.d("fetchPopularMoviesError", "${error.message}")
                    }
                ))

    }

    fun toggleMovieFavouredState(movieId: String) {
            toggleFavouredStateUseCase(movieId)
    }

    fun getMovieFavouredState(movieId: String) {
        disposable.add(
            getFavouredState(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { favouredState ->
                        _movieToggleState.value = favouredState
                        Log.d("fetchPopularMoviesError", "$favouredState")

                    },
                    { error ->
                        Log.d("fetchPopularMoviesError", "${error.message}")

                    }
                )
        )
    }

    fun saveFavouredMovies(favouredMovie: FavouredMovie) {
        saveFavouredStateUseCase(favouredMovie)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}