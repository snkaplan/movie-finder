package com.sk.moviefinder.domain.model.search

data class Movie(
    val id: String?,
    val title: String,
    val year: String,
    val type: String,
    val poster: String?,
)