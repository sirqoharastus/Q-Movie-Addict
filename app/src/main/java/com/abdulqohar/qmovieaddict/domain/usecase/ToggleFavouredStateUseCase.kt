package com.abdulqohar.qmovieaddict.domain.usecase

import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import javax.inject.Inject

class ToggleFavouredStateUseCase @Inject constructor(
    private val repository: QMovieRepository
) {
    operator fun invoke(movieId: String) {
        repository.toggleFavouredState(movieId)
    }
}