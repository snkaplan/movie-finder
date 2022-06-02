package com.sk.moviefinder.domain.model.detail

data class MovieDetail(
    val genre: String,
    val imdbVotes: String?,
    val description: String,
    val poster: String?,
    val runtime: String,
    val title: String,
    val year: String,
) {
}