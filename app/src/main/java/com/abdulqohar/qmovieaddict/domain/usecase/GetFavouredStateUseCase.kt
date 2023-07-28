package com.abdulqohar.qmovieaddict.domain.usecase

import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetFavouredStateUseCase @Inject constructor(
    private val repository: QMovieRepository
) {
    operator fun invoke(movieId: String): Flowable<Boolean> = repository.getFavouredState(movieId)
}