package com.sk.moviefinder.data.repository

import com.sk.moviefinder.data.api.MovieService
import com.sk.moviefinder.domain.model.*
import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    override suspend fun searchMovieByName(movieName: String): Result<MovieSearchResult> {
        return try {
            val searchMovie = movieService.searchMovie(movieName)
            return Success(searchMovie.mapToDomainModel())
        } catch (e: Exception) {
            Failure(HttpError(Throwable("Movie Not Found")))
        }
    }

    override suspend fun getMovieDetails(id: String): Result<MovieDetail> {
        return try {
            val movieDetailById = movieService.getMovieDetailById(id)
            Success(movieDetailById.mapToDomainModel())
        } catch (e: Exception) {
            return Failure(HttpError(Throwable("Get detail request failed")))
        }
    }
}