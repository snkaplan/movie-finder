package com.sk.moviefinder.domain.usecase

import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.model.Result

interface MovieDetailUseCase {
    suspend fun getMovieDetail(id: String): Result<MovieDetail>
}