package com.sk.moviefinder.domain.repository

interface MovieRepository {

    fun searchMovieByName(movieName: String)
    fun getMovieDetails()
}