package com.thaariq.happy5movie.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thaariq.happy5movie.data.remote.ApiConfig
import com.thaariq.happy5movie.data.responses.CreditsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreditViewModel : ViewModel() {

    val credits = MutableLiveData<CreditsResponse>()

    fun credits(movieId : Int){
        ApiConfig().getApiService().getCredits(movieId).enqueue(object  : Callback<CreditsResponse>{
            override fun onResponse(
                call: Call<CreditsResponse>,
                response: Response<CreditsResponse>
            ) {
                if (response.isSuccessful){
                    credits.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                Log.e("DataCredits", t.message.toString() )
            }

        })
    }
}