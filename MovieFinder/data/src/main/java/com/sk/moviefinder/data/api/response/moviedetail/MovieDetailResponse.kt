package com.sk.moviefinder.data.api.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.sk.moviefinder.data.base.DomainMapper
import com.sk.moviefinder.domain.model.detail.MovieDetail

data class MovieDetailResponse(
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Awards")
    val awards: String,
    @SerializedName("BoxOffice")
    val boxOffice: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("DVD")
    val dVD: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Genre")
    val genre: String,
    val imdbID: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Metascore")
    val metascore: String?,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Production")
    val production: String,
    @SerializedName("Rated")
    val rated: String,
    @SerializedName("Ratings")
    val ratings: List<Rating>,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Response")
    val response: String,
    @SerializedName("Runtime")
    val runtime: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Website")
    val website: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Year")
    val year: String,
) : DomainMapper<MovieDetail> {
    override fun mapToDomainModel(): MovieDetail {
        return MovieDetail(genre, imdbVotes, plot, poster, runtime, title, year)
    }
}