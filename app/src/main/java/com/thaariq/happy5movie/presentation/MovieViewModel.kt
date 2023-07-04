package com.thaariq.happy5movie.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thaariq.happy5movie.data.remote.ApiConfig
import com.thaariq.happy5movie.data.responses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    val genre = MutableLiveData<List<GenreItem>>()
    val showingMovie = MutableLiveData<List<ShowingMovie>>()
    val topRatedMovie = MutableLiveData<List<TopRatedMovie>>()
    val movieDetail = MutableLiveData<DetailResponse>()

    fun genre(){
        ApiConfig().getApiService().getGenres().enqueue(object : Callback<GenreResponse>{
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful){
                    genre.postValue(response.body()?.genres!!)
                }
            }
            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e("DataGenre", t.message.toString() )
            }
        })
    }

    fun showingMovie(){
        ApiConfig().getApiService().getShowing().enqueue(object : Callback<ShowingMovieResponse>{
            override fun onResponse(
                call: Call<ShowingMovieResponse>,
                response: Response<ShowingMovieResponse>
            ) {
                if (response.isSuccessful){
                    showingMovie.postValue(response.body()?.results!!)
                }
            }
            override fun onFailure(call: Call<ShowingMovieResponse>, t: Throwable) {
                Log.e("DataShowing", t.message.toString())
            }
        })
    }

    fun topRatedMovie(){
        ApiConfig().getApiService().getTopRated().enqueue(object  : Callback<TopRatedMovieResponse>{
            override fun onResponse(
                call: Call<TopRatedMovieResponse>,
                response: Response<TopRatedMovieResponse>
            ) {
                if (response.isSuccessful){
                    topRatedMovie.postValue(response.body()?.results!!)
                }
            }

            override fun onFailure(call: Call<TopRatedMovieResponse>, t: Throwable) {
                Log.e("DataTop", t.message.toString())
            }

        })
    }

    fun detail(movie_id : Int){
        ApiConfig().getApiService().getDetails(movie_id).enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful){
                    movieDetail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("DataDetail", t.message.toString())
            }

        })
    }
}