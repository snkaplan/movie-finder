package com.sk.moviefinder.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movies")
    fun searchMovie(@Query("movie_name") name: String): Call<String>
}
