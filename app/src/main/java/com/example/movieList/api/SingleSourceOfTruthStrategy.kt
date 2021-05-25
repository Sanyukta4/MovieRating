package com.example.movieList.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.movieList.db.entity.Results
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T, A> getLiveData(
    databaseQuery: () -> LiveData<T>?,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        Log.i("Test","Firing database query")
        val source = databaseQuery.invoke()?.map {
            Log.i("Test","mapping database query data")
            Result.success(it)
        }
        Log.i("Test","database query fired")
        emitSource(source!!)

        Log.i("Test","making network call")
        val responseStatus = networkCall.invoke()
        Log.i("Test","network call finished")
        if (responseStatus.status == Result.Status.SUCCESS) {
            Log.i("Test","Inserting data to database")
            saveCallResult(responseStatus.data!!)
            Log.i("Test","records inserted into database")
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }

fun updateRating(
    databaseQuery: () -> Unit
): LiveData<Result<String>> =
    liveData(Dispatchers.IO) {
        Log.i("UpdateTest","Updating")
        emit(Result.loading<String>())
        val result=databaseQuery.invoke()
        Log.i("UpdateTest","Updating $result")
        emit(Result.success("Success"))
    }


