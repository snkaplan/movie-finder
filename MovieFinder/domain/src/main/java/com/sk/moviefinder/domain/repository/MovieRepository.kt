package com.sk.moviefinder.domain.repository

import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.model.Result

interface MovieRepository {

    suspend fun searchMovieByName(movieName: String): Result<MovieSearchResult>
    suspend fun getMovieDetails(id: String): Result<MovieDetail>
    suspend fun getMoreMovie(movieName: String, page: Int): Result<MovieSearchResult>
}