package com.example.madlevel6task2.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val success = MutableLiveData<Boolean>()

    /**
     * This property points direct to the LiveData in the repository, that value
     * get's updated when user clicks FAB. This happens through the getTriviaNumber() in this class :)
     */
    val movies = movieRepository.movies
    val movie = movieRepository.movie

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from view for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getMovies(movieYear: Int) {
        viewModelScope.launch {
            try {
                movieRepository.getAllMovies(movieYear)
                success.value = true
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                success.value = false
                Log.e("Getting movies error", error.cause.toString())
            }
        }
    }

    fun getMovie(movieID: Int) {
        viewModelScope.launch {
            try {
                movieRepository.getMovie(movieID)
                success.value = true
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                success.value = false
                Log.e("Getting movie error", error.cause.toString())
            }
        }
    }
}