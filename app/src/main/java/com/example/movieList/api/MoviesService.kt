package com.example.movieList.api

import com.example.movieList.db.entity.MoviesEntity
import retrofit2.Response
import retrofit2.http.*

interface MoviesService {

    @GET("popular")
        suspend fun getMoviesData(@Query("api_key") apiKey:String,@Query("language") language:String ,@Query("page") page:Int ): Response<MoviesEntity>

}

