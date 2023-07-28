package com.abdulqohar.qmovieaddict.domain.usecase

import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.domain.repository.QMovieRepository
import javax.inject.Inject

class SavedFavouredMovieUseCase @Inject constructor(
    private val repository: QMovieRepository
) {
    operator fun invoke(favouredMovie: FavouredMovie) {
        repository.saveFavouredMovie(favouredMovie)
    }
}