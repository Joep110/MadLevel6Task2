package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.model.MovieResponse
import com.example.madlevel6task2.service.MovieApiService
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movie: MutableLiveData<MovieResponse> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movie: LiveData<MovieResponse>
        get() = _movie


    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}