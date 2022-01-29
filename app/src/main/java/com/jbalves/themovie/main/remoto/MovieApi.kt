package com.jbalves.themovie.main.remoto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") tmdbToken: String): Call<BaseResponse<List<MovieResponse>>>
}