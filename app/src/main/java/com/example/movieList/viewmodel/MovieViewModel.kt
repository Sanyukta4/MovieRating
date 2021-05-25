package com.example.movieList.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.movieList.db.entity.Results
import com.example.movieList.repository.MoviesRepository

class MovieViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    var results: Results? = null

    fun getMovieData() = moviesRepository.getMovieData()

    fun insertRating() = moviesRepository.updateMovie(results!!)


}