package com.thaariq.happy5movie.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thaariq.happy5movie.data.remote.ApiConfig
import com.thaariq.happy5movie.data.responses.SearchItem
import com.thaariq.happy5movie.data.responses.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val searchMovie =MutableLiveData<List<SearchItem>>()

    fun search(query : String){
        ApiConfig().getApiService().searchMovie(query).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful){
                    searchMovie.postValue(response.body()?.results!!)
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("DataSearch", t.message.toString() )
            }
        })
    }

}