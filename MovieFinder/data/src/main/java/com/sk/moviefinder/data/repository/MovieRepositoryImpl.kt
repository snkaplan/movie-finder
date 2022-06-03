package com.sk.moviefinder.data.repository

import com.sk.moviefinder.data.api.MovieService
import com.sk.moviefinder.data.utils.GENERAL_NETWORK_ERROR
import com.sk.moviefinder.data.utils.MAX_PAGE_ERROR
import com.sk.moviefinder.data.utils.MOVIE_NOT_FOUND_ERROR
import com.sk.moviefinder.domain.model.*
import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    /**
     * Searches the movies with given name by using OMDB API
     */
    override suspend fun searchMovieByName(movieName: String): Result<MovieSearchResult> {
        return try {
            val searchMovie = movieService.searchMovie(movieName)
            return Success(searchMovie.mapToDomainModel())
        } catch (e: Exception) {
            Failure(HttpError(Throwable(MOVIE_NOT_FOUND_ERROR)))
        }
    }

    /**
     * Fetches the movie with given id by using OMDB API
     */
    override suspend fun getMovieDetails(id: String): Result<MovieDetail> {
        return try {
            val movieDetailById = movieService.getMovieDetailById(id)
            Success(movieDetailById.mapToDomainModel())
        } catch (e: Exception) {
            return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    /**
     * Searches the movies with given name and page by using OMDB API
     */
    override suspend fun getMoreMovie(movieName: String, page: Int): Result<MovieSearchResult> {
        return try {
            val movies = movieService.getMoreMovie(movieName, page)
            return Success(movies.mapToDomainModel())
        } catch (e: Exception) {
            Failure(HttpError(Throwable(MAX_PAGE_ERROR)))
        }
    }
}