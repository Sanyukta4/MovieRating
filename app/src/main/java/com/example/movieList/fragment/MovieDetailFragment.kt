package com.example.movieList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movieList.databinding.FragmentMovieDetailFragmentBinding
import com.example.movieList.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailFragmentBinding
    private val movieViewModel: MovieViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailFragmentBinding.inflate(inflater, container, false)
        binding.results = movieViewModel.results

        try {
            binding.ratingBar.rating = movieViewModel.results?.rating!!
        }catch (e:Exception){
            e.printStackTrace()
        }

        binding.ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                movieViewModel.results?.rating = rating
                movieViewModel.insertRating().observe(viewLifecycleOwner, { result ->

                })
            }
        return binding.root

    }

}