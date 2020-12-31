package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.vm.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAddReminderResult()
        observeMovie()
    }

    private fun observeAddReminderResult() {
        arguments?.let {
            val movieId = it.getString(MOVIE_BUNDLE_KEY)
            if (movieId != null) {
                val id = movieId.toInt()
                viewModel.getMovie(id)
            }
        }
    }

    private fun observeMovie() {
        viewModel.movie.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                val imageSource = "https://image.tmdb.org/t/p/original/%s";
                Glide.with(it1)
                    .load(String.format(imageSource, it.backdrop_path))
                    .into(backdropImage)
                Glide.with(it1)
                    .load(String.format(imageSource, it.poster_path))
                    .into(imgMovieBanner)
            }
            txtMovieTitle.text = it.title
            movieDate.text = it.release_date
            txtViewRating.text = it.vote_average.toString()
            txtMovieDescription.text = it.overview
        }
    }


}