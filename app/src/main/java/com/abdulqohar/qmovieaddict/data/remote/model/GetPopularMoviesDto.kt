package com.abdulqohar.qmovieaddict.data.remote.model

data class GetPopularMoviesDto(
    val page: Int? = 0,
    val results: List<Result>? = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)