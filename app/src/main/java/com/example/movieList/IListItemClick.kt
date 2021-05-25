package com.example.movieList

import com.example.movieList.db.entity.Results


interface IListItemClick {

    fun viewMovieData(results: Results)
}