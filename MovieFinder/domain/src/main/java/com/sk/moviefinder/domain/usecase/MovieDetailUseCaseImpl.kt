package com.sk.moviefinder.domain.usecase

import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.repository.MovieRepository
import com.sk.moviefinder.domain.model.Result

class MovieDetailUseCaseImpl (private val movieRepository: MovieRepository) :
    MovieDetailUseCase {
    override suspend fun getMovieDetail(id: String): Result<MovieDetail> {
        return movieRepository.getMovieDetails(id)
    }
}