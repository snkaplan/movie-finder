package com.sk.moviefinder.domain.usecase

import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.repository.MovieRepository
import com.sk.moviefinder.domain.model.Result


class SearchMovieUseCaseImpl (private val movieRepository: MovieRepository) :
    SearchMovieUseCase {
    override suspend fun searchMovie(name: String): Result<MovieSearchResult> {
        return movieRepository.searchMovieByName(name)
    }
}