package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.MovieResponse
import retrofit2.http.GET

interface MovieApiService {

    @GET("/3/discover/movie")
    suspend fun getMovies(): MovieResponse
}