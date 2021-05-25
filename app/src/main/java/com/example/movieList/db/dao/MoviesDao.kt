package com.example.movieList.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieList.db.entity.MoviesEntity
import com.example.movieList.db.entity.Results

@Dao
interface MoviesDao {
    @Query("SELECT * FROM Results")
    fun getAll(): LiveData<List<Results>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(repositoryEntity: List<Results>)

    @Update(onConflict =  OnConflictStrategy.IGNORE)
    fun updateMovie(repositoryEntity: Results)



}