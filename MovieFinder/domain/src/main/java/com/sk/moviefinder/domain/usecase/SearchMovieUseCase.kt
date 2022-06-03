package com.sk.moviefinder.domain.usecase

import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.model.Result

interface SearchMovieUseCase {
    suspend fun searchMovie(name: String): Result<MovieSearchResult>
    suspend fun getMoreMovie(name: String, page: Int): Result<MovieSearchResult>
}