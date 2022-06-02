package com.sk.moviefinder.domain.model.search

data class MovieSearchResult(
    val totalResults: Int,
    val movies: ArrayList<Movie>,
)