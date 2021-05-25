package com.example.movieList.repository

import androidx.lifecycle.distinctUntilChanged
import com.example.movieList.api.*
import com.example.movieList.db.dao.MoviesDao
import com.example.movieList.db.entity.Results
import com.example.movieList.util.API_KEY
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDb: MoviesDao,
    private val movieApiClient: MovieApiClient
) {


    fun getMovieData() = getLiveData(
        databaseQuery = { moviesDb.getAll() },
        networkCall = { movieApiClient.fetchMovieData(API_KEY) },
        saveCallResult = { moviesDb.insertAll(it.results) }).distinctUntilChanged()

      fun updateMovie(results: Results) = updateRating { moviesDb.updateMovie(results) }


}

