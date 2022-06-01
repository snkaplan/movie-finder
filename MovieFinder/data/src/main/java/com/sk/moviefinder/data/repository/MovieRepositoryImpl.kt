package com.sk.moviefinder.data.repository

import com.sk.moviefinder.data.api.MovieService
import com.sk.moviefinder.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    override fun searchMovieByName(movieName: String) {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails() {
        TODO("Not yet implemented")
    }
}