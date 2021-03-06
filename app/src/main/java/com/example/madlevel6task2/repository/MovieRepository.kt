package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.model.MovieResponse
import com.example.madlevel6task2.service.MovieApiService

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()
    private val apiKey = "3c0488d8661d18c0c68d9cd84e90859f"
    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    private val _movies: MutableLiveData<MovieResponse> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movies: LiveData<MovieResponse>
        get() = _movies

    val movie: LiveData<Movie>
        get() = _movie

    suspend fun getMovie(movieID: Int) {
        try {
            val result =
                movieApiService.getMovie(movie_id = movieID, api_key = apiKey)
            _movie.value = result
        } catch (error: Throwable) {

        }
    }

    suspend fun getAllMovies(movieYear: Int) {
        try {
            val result =
                movieApiService.getMovies(api_key = apiKey, movieYear = movieYear)
            _movies.value = result
        } catch (error: Throwable) {

        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}