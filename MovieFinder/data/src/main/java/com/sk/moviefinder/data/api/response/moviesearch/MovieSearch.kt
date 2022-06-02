package com.sk.moviefinder.data.api.response.moviesearch


import com.google.gson.annotations.SerializedName
import com.sk.moviefinder.data.base.DomainMapper
import com.sk.moviefinder.domain.model.search.Movie

data class MovieSearch(
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Year")
    val year: String,
) : DomainMapper<Movie> {
    override fun mapToDomainModel(): Movie {
        return Movie(imdbID, title, year, type, poster)
    }
}