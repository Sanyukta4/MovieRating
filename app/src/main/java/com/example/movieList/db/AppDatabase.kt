package com.example.movieList.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieList.ProfileTypeConverter
import com.example.movieList.db.dao.MoviesDao
import com.example.movieList.db.entity.MoviesEntity
import com.example.movieList.db.entity.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters( ProfileTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app.db").build()
    }
}



