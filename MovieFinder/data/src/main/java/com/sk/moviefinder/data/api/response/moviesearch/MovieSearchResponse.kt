package com.sk.moviefinder.data.api.response.moviesearch


import com.google.gson.annotations.SerializedName
import com.sk.moviefinder.data.base.DomainMapper
import com.sk.moviefinder.domain.model.search.Movie
import com.sk.moviefinder.domain.model.search.MovieSearchResult

data class MovieSearchResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val movieSearchResults: List<MovieSearch>,
    val totalResults: Int,
) : DomainMapper<MovieSearchResult> {
    override fun mapToDomainModel(): MovieSearchResult {
        val movies = arrayListOf<Movie>()
        for (result in movieSearchResults) {
            movies.add(result.mapToDomainModel())
        }
        return MovieSearchResult(totalResults, movies)
    }

}