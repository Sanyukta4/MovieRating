package com.example.movieList.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieList.IListItemClick
import com.example.movieList.R
import com.example.movieList.adapter.MovieDataAdapter
import com.example.movieList.api.Result
import com.example.movieList.databinding.FragmentMoviesListBinding
import com.example.movieList.db.entity.Results
import com.example.movieList.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), IListItemClick {

    private var mMovieDataAdapter = MovieDataAdapter(this@MovieListFragment as IListItemClick)
    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var binding: FragmentMoviesListBinding
    private var pKId: Int = 0
    private lateinit var movieList: List<Results>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        setAdapter()
        getMovieData()


        return binding.root
    }

    private fun setAdapter() {
        binding.rvPost.adapter = mMovieDataAdapter
    }

    private fun getMovieData() {
        movieViewModel.getMovieData().observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data
                    try {
                        movieList = result.data!!
                        mMovieDataAdapter.submitList(movieList)
                    } catch (e: Exception) {
                    }
                }
                Result.Status.ERROR -> {

                }
                Result.Status.LOADING -> {
                    result.message?.let { Log.i("Loading", it) }
                }
            }
        })
    }

    override fun viewMovieData(results: Results) {
        movieViewModel.results = results
        findNavController().navigate(R.id.action_NewsListFragment_to_movieDetailFragment)

    }


}