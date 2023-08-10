package com.abdulqohar.qmovieaddict.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.GetPopularMoviesDto
import com.abdulqohar.qmovieaddict.domain.usecase.GetFavouredMoviesUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.GetMovieDetailsUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.GetPopularMoviesDtoUseCase
import com.abdulqohar.qmovieaddict.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesDtoUseCase: GetPopularMoviesDtoUseCase,
    private val getFavouredMoviesUseCase: GetFavouredMoviesUseCase
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovieEntity>>()
    val popularMovies: LiveData<List<MovieEntity>> get() = _popularMovies

    private val _popularMoviesDto = MutableLiveData<GetPopularMoviesDto>()
    val popularMoviesDto: LiveData<GetPopularMoviesDto> get() = _popularMoviesDto

    private val _favouredMovies = MutableLiveData<List<FavouredMovie>>()
    val favouredMovies: LiveData<List<FavouredMovie>> get() = _favouredMovies


    private val disposable = CompositeDisposable()


    fun fetchPopularMovies() {
        disposable.add(
            getPopularMoviesUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { movies ->
                        _popularMovies.value = movies
                    },
                    { error ->

                        Log.d("fetchPopularMoviesError", "${error.message}")
                    }
                ))

    }

    fun getPopularMoviesDto() {
        getPopularMoviesDtoUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieDto ->
                    _popularMoviesDto.value = movieDto
                },
                { error ->
                    Log.d("fetchPopularMoviesError", "${error.message}")

                }
            ).dispose()
    }

    fun getFavouredMoviesDto() {
        disposable.add(
            getFavouredMoviesUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { favourites ->
                        _favouredMovies.value = favourites
                    },
                    { error ->
                        Log.d("fetchPopularMoviesError", "${error.message}")

                    }
                )
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

//    private val disposable = CompositeDisposable()

}