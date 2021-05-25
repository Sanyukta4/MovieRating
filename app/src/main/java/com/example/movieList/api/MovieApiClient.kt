package com.example.movieList.api

import androidx.lifecycle.MutableLiveData
import com.example.movieList.util.API_KEY
import javax.inject.Inject

class MovieApiClient @Inject constructor(private val service: MoviesService) : BaseDataSource() {

    suspend fun fetchMovieData(API_KEY:String) = getResult {
        service.getMoviesData(API_KEY,"en-US",1)
    }
}