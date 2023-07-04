package com.thaariq.happy5movie.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thaariq.happy5movie.data.remote.ApiConfig
import com.thaariq.happy5movie.data.responses.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel :ViewModel() {

    val movieDetail = MutableLiveData<DetailResponse>()

    fun detail(movieId : Int){
        ApiConfig().getApiService().getDetails(movieId).enqueue(object : Callback<DetailResponse> {
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