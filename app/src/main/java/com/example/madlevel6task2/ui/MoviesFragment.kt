package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.ui.recyclers.adapters.MovieAdapter
import com.example.madlevel6task2.vm.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

const val MOVIE_KEY = "MOVIE_KEY"
const val MOVIE_BUNDLE_KEY = "MOVIE_BUNDLE_KEY"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment() {
    private val movies = arrayListOf<Movie>()
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movies, ::onSeeMovieDetails)
        rvMovies.layoutManager = gridLayoutManager
        rvMovies.adapter = movieAdapter
        btnSubmit.setOnClickListener {
            onSearchMovies()
        }
    }

    private fun onSearchMovies() {
        val movieYear = txtMovieYear.text.toString().toInt()
        viewModel.getMovies(movieYear)

        viewModel.movies.observe(viewLifecycleOwner) {
            movies.clear()
            val movieList: List<Movie> = it.results
            movies.addAll(movieList)
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun onSeeMovieDetails(movie: Movie) {

        val bundle = bundleOf(Pair(MOVIE_BUNDLE_KEY, movie.id))
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment, bundle
        )
    }
}