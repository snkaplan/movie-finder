package com.sk.moviefinder.data.api

import com.sk.moviefinder.data.api.response.moviedetail.MovieDetailResponse
import com.sk.moviefinder.data.api.response.moviesearch.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun searchMovie(@Query("s") name: String): MovieSearchResponse

    @GET("/")
    suspend fun getMovieDetailById(@Query("i") id: String): MovieDetailResponse
}
