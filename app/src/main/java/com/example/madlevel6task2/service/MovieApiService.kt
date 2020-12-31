package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("primary_release_year") movieYear: Int
    ): MovieResponse
}