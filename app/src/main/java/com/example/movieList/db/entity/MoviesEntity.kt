package com.example.movieList.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Results>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
)
